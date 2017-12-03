package com.example.omarfedah.tasker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import android.database.Cursor;
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
	public PurchasableObject(String name, Boolean isGrocery, Boolean isOwned) throws UniqueIDException{
		GUI guiInst = GUI.getInstance();
		boolean isUniqueID = false;
		try {
			guiInst.checkUniqueID(name, "object");
			isUniqueID = true;
		} catch (UniqueIDException e) {}
		if (isUniqueID) {
			this.name = name;
			String values = "VALUES ('" + name + "'," + (isGrocery ? 1 : 0) + "," + (isOwned ? 1 : 0) + ")";
			String sqlstmt = "INSERT INTO object(name, isGrocery, isOwned) " + values;
			guiInst.databaseUpdate(sqlstmt);
		}
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
	public Boolean getIsGrocery() {
		String sqlstmt = "SELECT isgrocery FROM object WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		QueryResult qr = guiInst.databaseQuery(sqlstmt);
		Cursor rs = qr.getResultSet();
		rs.moveToFirst();
		return rs.getInt(1) == 1;
	}

	/**
	 * Getter for PurchasableObject's isOwned status.
	 * @return Boolean representation of whether the PurchasableObject is owned.
	 */
	public Boolean getIsOwned() {
		String sqlstmt = "SELECT isowned FROM object WHERE name = '" + name + "'";
		GUI guiInst = GUI.getInstance();
		QueryResult qr  = guiInst.databaseQuery(sqlstmt);
		Cursor rs = qr.getResultSet();
		rs.moveToFirst();
		return rs.getInt(2) == 1;
	}

	/**
	 * Used to edit the name of an existing PurchasableObject.
	 * @param newName String containing the PurchasableObject's new name.
	 */
	public void setObjectName(String newName) throws UniqueIDException {
		GUI guiInst = GUI.getInstance();
		boolean isUniqueID = false;
		try {
			guiInst.checkUniqueID(newName, "object");
			isUniqueID = true;
		} catch (UniqueIDException e) {}
		if (isUniqueID) {
			String sqlstmt = "UPDATE object SET name = '" + newName + "' WHERE name = " + "," + name + ",";
			guiInst.databaseUpdate(sqlstmt);
			this.name = newName;
		}
	}

	/**
	 * Used to edit the 'isGrocery' status of an existing PurchasableObject.
	 * @param NewIsGrocery Boolean representation of the PurchasableObject's new 'isGrocery'
	 *                     status.
	 */
	public void setIsGrocery(Boolean NewIsGrocery) {
		String sqlstmt = "UPDATE object SET isgrocery = " + (NewIsGrocery ? 1 : 0) + " WHERE name = " + "," + name + ",";
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the 'isOwned' status of an existing PurchasableObject.
	 * @param NewIsOwned  Boolean representation of the PurchasableObject's new 'isOwned'
	 *                   status.
	 */
	public void setIsOwned(Boolean NewIsOwned) {
		String sqlstmt = "UPDATE object SET isowned = " + (NewIsOwned ? 1 : 0) + " WHERE name = " + "," + name + ",";
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}
}
