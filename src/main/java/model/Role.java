package model;

public enum Role {
	admin,
	employee,
	user;
	
	public String getStringRole() {
		if(this == admin) 
			return "admin";
		else if(this == employee)
			return "employee";
		else if(this == user)
			return "user";
		return null;
	}
	
	public Role getRoleByString(String role) {
		if(role.equalsIgnoreCase("admin"))
			return Role.admin;
		else if(role.equalsIgnoreCase("employee"))
			return Role.employee;
		else if(role.equalsIgnoreCase("user"))
			return Role.user;
		return null;
	}
}