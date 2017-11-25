package com.example.omarfedah.tasker;

import java.sql.SQLException;
import java.sql.ResultSet;


//missing proper imports
//requires external methods databaseQuery(String), databaseUpdate(String)
 class User extends Collectable {

	private String name;
	//database String Icon;
	//database String password;
	//database com.example.omarfedah.tasker.TaskList userList;

	public User(String name, String icon, String password ){
		this.name = name;
		String values = "VALUES (" + name + "," + icon + "," + password + ")";
		String sqlstmt = "INSERT INTO user(name, icon, password) " + values;
		GUI.databaseUpdate(sqlstmt);
	}

	public User(String name) {
		this.name = name;
	}

	public String getUserName() {
	 return name;
    }

	public String getIcon() {
		String sqlstmt = "SELECT icon FROM user WHERE name = " + name;
		ResultSet rs = GUI.databaseQuery(sqlstmt);
		try {
		 String icon = rs.getString("icon");
         return icon;
		} catch (SQLException e) {
		 return "";
        }
	}

	public void setName(String newName) {
		String sqlstmt = "UPDATE user SET name = " + newName + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
		this.name = newName;
	}

	public void setIcon(String newIcon) {
		String sqlstmt = "UPDATE user SET icon = " + newIcon + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}

	public void setPassword(String newPassword) {
		String sqlstmt = "UPDATE user SET password = " + newPassword + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}

}
