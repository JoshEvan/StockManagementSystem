package com.joshua.StockManagementSystem.joseph_impl.infrastructure.adapter;

import com.joshua.StockManagementSystem.auth.User;
import com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout.UserDataEntity;
import com.joshua.StockManagementSystem.security.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class UserAdapter {
	private final static Logger logger = LoggerFactory.getLogger(UserAdapter.class);

	public static UserDataEntity resulltSetToDataEntity(ResultSet resultSet) {
		try{
			return new UserDataEntity(
				resultSet.getString(UserDataEntity.USERNAME),
				resultSet.getString(UserDataEntity.PASSWORD),
				resultSet.getString(UserDataEntity.ROLE),
				resultSet.getBoolean(UserDataEntity.ACC_NON_EXP),
				resultSet.getBoolean(UserDataEntity.ACC_NON_LOCKED),
				resultSet.getBoolean(UserDataEntity.CRED_NON_EXP),
				resultSet.getBoolean(UserDataEntity.IS_ENABLED)
			);
		} catch (SQLException throwable) {
			logger.error(String.valueOf(throwable));
			return  new UserDataEntity();
		}
	}

	public static List<User> dataEntitiesToModels(List<UserDataEntity> userDataEntities) {
		return userDataEntities.stream().map(
			userDataEntity -> new User(
				UserRole.valueOf(userDataEntity.getRole()).getGrantedAuthorities(),
				userDataEntity.getPassword(),
				userDataEntity.getUsername(),
				userDataEntity.getAccNonExp(),
				userDataEntity.getAccNonLocked(),
				userDataEntity.getCredNonExp(),
				userDataEntity.getEnabled()
			)
		).collect(Collectors.toList());
	}

}
