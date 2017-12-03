package com.example.omarfedah.tasker;

import android.database.Cursor;
import android.util.Log;

import java.sql.SQLException;


 class Task extends Collectable {

	private String name;
	//database int endDateTime;
	//database boolean isCompleted;
	//database String note;
	//database String objectList;
	//database String creator
	//database String assignedTo

	/**
	 * Constructor for Task object. Adds the newly created task to the database and returns
	 * an instance of the new Task object.
	 * @param name String containing the task name.
	 * @param endDateTime Long representing the end date and time of the task, formatted
	 *                    as YYYYMMDDHHmmL. Uses 24 hour time.
	 * @param isCompleted Boolean for whether or the task has been completed.
	 * @param note String containing a note associated to the task.
	 * @param objectList ObjectList containing any objects associated to the task.
	 * @param creator User that created the task.
	 * @param assignedTo User the task is assigned to.
	 */
	public Task(String name, long endDateTime, Boolean isCompleted, String note, ObjectList objectList, User creator, User assignedTo) throws UniqueIDException {
		GUI guiInst = GUI.getInstance();
		boolean isUniqueID = false;
		try {
			guiInst.checkUniqueID(name, "task");
			isUniqueID = true;
		} catch (UniqueIDException e) {
			isUniqueID = false;
		}
		if (isUniqueID) {
			this.name = name;
			String values = "VALUES('" + name + "', " + endDateTime + ", " + (isCompleted ? 1 : 0) + ", '" + note + "', '" + objectList.asString() + "', '" + creator.getUserName() + "', '" + assignedTo.getUserName() + "')";
			String sqlstmt = "INSERT INTO task(name,enddatetime, iscompleted, note, objectlist, creator, assignedto) " + values;
			guiInst.databaseUpdate(sqlstmt);
		}
	}

	/**
	 * Secondary constructor for Task object. Returns an instance of a new Task object but
	 * does not create a new entry in the database.
	 * @param name String containing the task name.
	 */
	public Task(String name) {
		this.name = name;
	}

	/**
	 * Getter for task name.
	 * @return String containing the task name.
	 */
	public String getTaskName() {
		return name;
	}

	/**
	 * Getter for task end date and time
	 * @return Long representation of datetime, formatted as YYYYMMDDHHmmL.
	 */
	public long getEndDateTime() {
		String sqlstmt = "SELECT enddatetime FROM task WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		QueryResult qr = guiInst.databaseQuery(sqlstmt);
		Cursor rs = qr.getResultSet();
		rs.moveToFirst();
		//fixme
		return rs.getInt(rs.getColumnIndex("enddatetime"));
	}

	/**
	 * Getter for task completed status.
	 * @return  Boolean representing task completed status.
	 */
	public Boolean getIsCompleted() {
		String sqlstmt = "SELECT iscompleted FROM task WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		QueryResult qr =guiInst.databaseQuery(sqlstmt);
		Cursor rs = qr.getResultSet();
		rs.moveToFirst();
		return (rs.getInt(2) == 1);
	}

	/**
	 * Getter for a task's associated ObjectList.
	 * @return ObjectList containing all objects associated with the task.
	 */
	public ObjectList getObjectList() {
		String sqlstmt = "SELECT objectlist FROM task WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		QueryResult qr = guiInst.databaseQuery(sqlstmt);
		Cursor rs = qr.getResultSet();
		rs.moveToFirst();
		String objectListString = rs.getString(4);
		String[] objects = objectListString.split("/");
		ObjectList objectList = new ObjectList();
		for (String obj : objects) {
		 objectList.add(new PurchasableObject(obj));
		}
		return objectList;
	}

	/**
	 * Getter for the User who created the task.
	 * @return User who created the task.
	 */
	public User getCreator() {
		String sqlstmt = "SELECT creator FROM task WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		QueryResult qr = guiInst.databaseQuery(sqlstmt);
		Cursor rs = qr.getResultSet();
		rs.moveToFirst();
		String creatorName = rs.getString(5);
        return new User(creatorName);
	}

	/**
	 * Getter for User the task is assigned to.
	 * @return User the task is assigned to.
	 */
	public User getAssignedTo() {
		String sqlstmt = "SELECT assignedto FROM task WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		QueryResult qr = guiInst.databaseQuery(sqlstmt);
		Cursor rs = qr.getResultSet();
		rs.moveToFirst();
		String assignedToName = rs.getString(6);
        return new User(assignedToName);
	}

	/**
	 * Used to edit the name of an existing task.
	 * @param newName String containing the new task name.
	 */
	public void setTaskName(String newName) throws UniqueIDException{
		GUI guiInst = GUI.getInstance();
		boolean isUniqueID = false;
		try {
			guiInst.checkUniqueID(newName, "task");
			isUniqueID = true;
		} catch (UniqueIDException e) {}
		if (isUniqueID) {
			String sqlstmt = "UPDATE task SET name = '" + newName + "' WHERE name = " + "'" + name + "'";
			guiInst.databaseUpdate(sqlstmt);
			this.name = newName;
		}
	}

	/**
	 * Used to edit the end date and time of an existing task.
	 * @param newEndDateTime Long representing the new end date and time, formatted as
	 *                       YYYYMMDDHHmmL.
	 */
	public void setEndDateTime(long newEndDateTime) {
		String sqlstmt = "UPDATE task SET enddatetime = " + newEndDateTime + " WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the completed status of an existing task.
	 * @param newIsCompleted Boolean representing the new completed status.
	 */
	public void setIsCompleted(Boolean newIsCompleted) {
		String sqlstmt = "UPDATE task SET iscompleted = " + (newIsCompleted ? 1 : 0) + " WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the ObjectList associated with an existing task.
	 * @param newObjectList ObjectList containing the new list of associated objects
	 */
	public void setObjectList(ObjectList newObjectList) {
		String sqlstmt = "UPDATE task SET objectlist = '" + newObjectList.asString() + "' WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the creator of an existing task.
	 * @param newCreator New User to be set as the creator.
	 */
	public void setCreator(User newCreator) {
		String sqlstmt = "UPDATE task SET creator = '" + newCreator.getUserName() + "' WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the user a task is assigned to.
	 * @param newAssignedTo New User for the task to be assigned to.
	 */
	public void setAssignedTo(User newAssignedTo) {
		String sqlstmt = "UPDATE task SET assignedto = '" + newAssignedTo.getUserName() + "' WHERE name = " + "'" + name + "'";
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}
}
