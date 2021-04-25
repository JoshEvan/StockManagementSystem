package com.joshua.StockManagementSystem.auth;

import com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter.UserAdapter;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.UserDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("postgresUser")
public class UserPostgresDataService implements UserDAO{

	private final JdbcTemplate jdbcTemplate;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserPostgresDataService(PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<User> selectUserByUsername(String username){
		final String sql = "SELECT * FROM "+ UserDataEntity.TABLE+ " WHERE " + UserDataEntity.USERNAME+ " = ?";
		User user = null;
		UserDataEntity u = jdbcTemplate.queryForObject(sql,new Object[]{username},
			((resultSet, i) -> {return UserAdapter.resulltSetToDataEntity(resultSet);})
			);
		if (u != null) {
			u.setPassword(passwordEncoder.encode(u.getPassword()));
			List<User> users = UserAdapter.dataEntitiesToModels(Arrays.asList(u));
			if (users.size() > 0) {
				user = users.get(0);
			}
		}
		return Optional.ofNullable(user);
	}
}
