package com.example.omarfedah.tasker;

import java.sql.SQLException;
import android.database.Cursor;


 class User extends Collectable {

	private String name;
	//database String Icon;
	//database String password;

	/**
	 * Constructor for User object. Adds the newly created User to the database and returns
	 * an instance of the new User object.
	 * @param name String containing the User's name
	 * @param icon String representaing of the User's selected icon.
	 * @param password String containing the user's password.
	 */
	public User(String name, String icon, String password ){
		this.name = name;
		String values = "VALUES (" + name + "," + icon + "," + password + ")";
		String sqlstmt = "INSERT INTO user(name, icon, password) " + values;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Secondary constructor for User object. Returns an insatnce of User without creating a
	 * new entry in the database.
	 * @param name String containing the User's name.
	 */
	public User(String name) {
		this.name = name;
	}

	/**
	 * Getter for the user's name.
	 * @return String containing the user's name.
	 */
	public String getUserName() {
	 return name;
    }

	/**
	 * Getter of the string representation of a user's icon.
	 * @return String representation of the user's icon.
	 */
	public String getIcon() {
		String sqlstmt = "SELECT icon FROM user WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		Cursor rs = guiInst.databaseQuery(sqlstmt);
		String icon = rs.getString(1);
		return icon;
	}

	/**
	 * Used to edit the name of an existing User.
	 * @param newName String containing the user's new name.
	 */
	public void setName(String newName) {
		String sqlstmt = "UPDATE user SET name = " + newName + " WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
		this.name = newName;
	}

	/**
	 * Used to edit the icon of an existing User.
	 * @param newIcon String representation of the user's new icon.
	 */
	public void setIcon(String newIcon) {
		String sqlstmt = "UPDATE user SET icon = " + newIcon + " WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the password of an existing user.
	 * @param newPassword String containing the user's new password.
	 */
	public void setPassword(String newPassword) {
		String sqlstmt = "UPDATE user SET password = " + newPassword + " WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}
}
