package com.example.omarfedah.tasker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


//requires external methods databaseQuery(String), databaseUpdate(String)

class PurchasableObject extends Collectable {

	private String name;
	//database boolean isGrocery;
	//database boolean isOwned;

	public PurchasableObject(String name, boolean isGrocery, boolean isOwned) {
		this.name = name;
		String values = "VALUES (" + name + "," + isGrocery + "," + isOwned + ")";
		String sqlstmt = "INSERT INTO object(name, isGrocery, isOwned) " + values;
		GUI.databaseUpdate(sqlstmt);
	}

	public PurchasableObject(String name) {
		this.name = name;
	}

	public String getObjectName() {
		return name;
	}

	public boolean getIsGrocery() {
		String sqlstmt = "SELECT isgrocery FROM object WHERE name = " + name;
		ResultSet rs = GUI.databaseQuery(sqlstmt);
		try {
			boolean isGrocery = rs.getBoolean("isgrocery");
			return isGrocery;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean getIsOwned() {
		String sqlstmt = "SELECT isowned FROM object WHERE name = " + name;
		ResultSet rs = GUI.databaseQuery(sqlstmt);
		try {
			boolean isOwned = rs.getBoolean("isowned");
			return isOwned;
		} catch (SQLException e ) {
			return false;
		}
	}

	public void setObjectName(String newName) {
		String sqlstmt = "UPDATE object SET name = " + newName + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
		this.name = newName;
	}

	public void setIsGrocery(boolean NewIsGrocery) {
		String sqlstmt = "UPDATE object SET isgrocery = " + NewIsGrocery + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}

	public void setIsOwned(boolean NewIsOwned) {
		String sqlstmt = "UPDATE object SET isowned = " + NewIsOwned + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}

	
}
