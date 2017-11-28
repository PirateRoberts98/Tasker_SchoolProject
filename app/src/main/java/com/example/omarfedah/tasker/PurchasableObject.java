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

	/**
	 * Constructor for PurchasableObject object. Adds the newly created PurchasableObject to
	 * the database and returns an instance of the new PurchasableObject.
	 * @param name String containing the name of the PurchasableObject
	 * @param isGrocery Boolean representation of whether the PurchasableObject is groceries.
	 * @param isOwned Boolean representation of whether the PurchasableObject is owned.
	 */
	public PurchasableObject(String name, boolean isGrocery, boolean isOwned) {
		this.name = name;
		String values = "VALUES (" + name + "," + isGrocery + "," + isOwned + ")";
		String sqlstmt = "INSERT INTO object(name, isGrocery, isOwned) " + values;
		GUI.databaseUpdate(sqlstmt);
	}

	/**
	 * Secondary constructor for PurchasableObject object. Returns an instance of the new
	 * PurchasableObject without adding it to the database.
	 * @param name String containing the name of the PurchasableObject.
	 */
	public PurchasableObject(String name) {
		this.name = name;
	}

	/**
	 * Getter for the PurchasableObject name.
	 * @return String containing the PurchasableObject name.
	 */
	public String getObjectName() {
		return name;
	}

	/**
	 * Getter for PurchasableObject's isGrocery status.
	 * @return Boolean representation of whether the PurchasableObject is groceries.
	 */
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

	/**
	 * Getter for PurchasableObject's isOwned status.
	 * @return Boolean representation of whether the PurchasableObject is owned.
	 */
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

	/**
	 * Used to edit the name of an existing PurchasableObject.
	 * @param newName String containing the PurchasableObject's new name.
	 */
	public void setObjectName(String newName) {
		String sqlstmt = "UPDATE object SET name = " + newName + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
		this.name = newName;
	}

	/**
	 * Used to edit the 'isGrocery' status of an existing PurchasableObject.
	 * @param NewIsGrocery Boolean representation of the PurchasableObject's new 'isGrocery'
	 *                     status.
	 */
	public void setIsGrocery(boolean NewIsGrocery) {
		String sqlstmt = "UPDATE object SET isgrocery = " + NewIsGrocery + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the 'isOwned' status of an existing PurchasableObject.
	 * @param NewIsOwned Boolean representation of the PurchasableObject's new 'isOwned'
	 *                   status.
	 */
	public void setIsOwned(boolean NewIsOwned) {
		String sqlstmt = "UPDATE object SET isowned = " + NewIsOwned + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}
}
