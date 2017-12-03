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
	 * @param icon String representing of the User's selected icon.
	 * @param password String containing the user's password.
	 */
	public User(String name, String icon, String password ) throws UniqueIDException{
		GUI guiInst = GUI.getInstance();
		boolean isUniqueID = false;
		try {
			guiInst.checkUniqueID(name, "user");
			isUniqueID = true;
		} catch (UniqueIDException e) {}
		if (isUniqueID) {
			this.name = name;
			String values = "VALUES ('" + name + "','" + icon + "','" + password + "')";
			String sqlstmt = "INSERT INTO user(name, icon, password) " + values;
			guiInst.databaseUpdate(sqlstmt);
		}
	}

	/**
	 * Secondary constructor for User object. Returns an instance of User without creating a
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
		String sqlstmt = "SELECT icon FROM user WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		QueryResult qr = guiInst.databaseQuery(sqlstmt);
		Cursor rs = qr.getResultSet();
		rs.moveToFirst();
		return rs.getString(1);
	}

	/**
	 * Used to edit the name of an existing User.
	 * @param newName String containing the user's new name.
	 */
	public void setName(String newName) throws UniqueIDException {
		GUI guiInst = GUI.getInstance();
		boolean isUniqueID = false;
		try {
			guiInst.checkUniqueID(newName, "user");
			isUniqueID = true;
		} catch (UniqueIDException e) {}
		if (isUniqueID) {
			String sqlstmt = "UPDATE user SET name = '" + newName + "' WHERE name = " + "'" + name + "'";
			guiInst.databaseUpdate(sqlstmt);
			this.name = newName;
		}
	}

	/**
	 * Used to edit the icon of an existing User.
	 * @param newIcon String representation of the user's new icon.
	 */
	public void setIcon(String newIcon) {
		String sqlstmt = "UPDATE user SET icon = '" + newIcon + "' WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the password of an existing user.
	 * @param newPassword String containing the user's new password.
	 */
	public void setPassword(String newPassword) {
		String sqlstmt = "UPDATE user SET password = '" + newPassword + "' WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}
}
