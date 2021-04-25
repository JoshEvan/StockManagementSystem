package com.joshua.StockManagementSystem.joseph_impl.infrastructure.flushout;

import com.joshua.StockManagementSystem.security.UserRole;

public class UserDataEntity {
	public final String SCHEMA = "joseph_user,public";
	public static final String TABLE = "users";
	public final int numColumns = 7;
	public static String USERNAME="username", PASSWORD="password", ROLE="role", ACC_NON_EXP="is_account_non_expired", ACC_NON_LOCKED = "is_account_non_locked", CRED_NON_EXP = "is_credential_non_expired", IS_ENABLED="is_enabled";
	private String username, password;
	private String role;
	private Boolean accNonExp, accNonLocked, credNonExp, isEnabled;
	public UserDataEntity() {
	}

	public UserDataEntity(String username, String password, String role, Boolean accNonExp, Boolean accNonLocked, Boolean credNonExp, Boolean isEnabled) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.accNonExp = accNonExp;
		this.accNonLocked = accNonLocked;
		this.credNonExp = credNonExp;
		this.isEnabled = isEnabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getAccNonExp() {
		return accNonExp;
	}

	public void setAccNonExp(Boolean accNonExp) {
		this.accNonExp = accNonExp;
	}

	public Boolean getAccNonLocked() {
		return accNonLocked;
	}

	public void setAccNonLocked(Boolean accNonLocked) {
		this.accNonLocked = accNonLocked;
	}

	public Boolean getCredNonExp() {
		return credNonExp;
	}

	public void setCredNonExp(Boolean credNonExp) {
		this.credNonExp = credNonExp;
	}

	public Boolean getEnabled() {
		return isEnabled;
	}

	public void setEnabled(Boolean enabled) {
		isEnabled = enabled;
	}
}
