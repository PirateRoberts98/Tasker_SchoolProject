package com.example.omarfedah.tasker;

import android.database.Cursor;
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
	 * @param endDateTime Integer representing the end date and time of the task, formatted
	 *                    as YYYYMMDDHHmm. Uses 24 hour time.
	 * @param isCompleted int Boolean for whether or the task has been completed.
	 * @param note String containing a note associated to the task.
	 * @param objectList ObjectList containing any objects associated to the task.
	 * @param creator User that created the task.
	 * @param assignedTo User the task is assigned to.
	 */
	public Task(String name, int endDateTime, int isCompleted, String note, ObjectList objectList, User creator, User assignedTo) {
		this.name = name;
		String values = "VALUES (" + name + "," + endDateTime + "," + isCompleted + "," + note + "," + objectList.asString() + "," + creator.getUserName() + "," + assignedTo.getUserName() + ")";
		String sqlstmt = "INSERT INTO task(name,enddatetime,iscompleted,note,objectlist,creator,assignedto) " + values;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
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
	 * @return Integer representation of datetime, formatted as YYYYMMDDHHmm.
	 */
	public int getEndDateTime() {
		String sqlstmt = "SELECT enddatetime FROM task WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		Cursor rs =guiInst.databaseQuery(sqlstmt);
		return rs.getInt(1);
	}

	/**
	 * Getter for task completed status.
	 * @return int Boolean representing task completed status.
	 */
	public int getIsCompleted() {
		String sqlstmt = "SELECT iscompleted FROM task WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		Cursor rs =guiInst.databaseQuery(sqlstmt);
		return rs.getInt(2);
	}

	/**
	 * Getter for a task's associated ObjectList.
	 * @return ObjectList containing all onjects associated with the task.
	 */
	public ObjectList getObjectList() {
		String sqlstmt = "SELECT objectlist FROM task WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		Cursor rs = guiInst.databaseQuery(sqlstmt);
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
		String sqlstmt = "SELECT creator FROM task WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		Cursor rs = guiInst.databaseQuery(sqlstmt);
		String creatorName = rs.getString(5);
        return new User(creatorName);
	}

	/**
	 * Getter for User the task is assigned to.
	 * @return User the task is assigned to.
	 */
	public User getAssignedTo() {
		String sqlstmt = "SELECT assignedto FROM task WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		Cursor rs = guiInst.databaseQuery(sqlstmt);
		String assignedToName = rs.getString(6);
        return new User(assignedToName);
	}

	/**
	 * Used to edit the name of an existing task.
	 * @param newName String containing the new task name.
	 */
	public void setTaskName(String newName) {
		String sqlstmt = "UPDATE task SET name = " + newName + " WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
		this.name = newName;
	}

	/**
	 * Used to edit the end date and time of an existing task.
	 * @param newEndDateTime Integer representing the new end date and time, formatted as
	 *                       YYYYMMDDHHmm.
	 */
	public void setEndDateTime(int newEndDateTime) {
		String sqlstmt = "UPDATE task SET enddatetime = " + newEndDateTime + " WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the completed status of an existing task.
	 * @param newIsCompleted int Boolean representing the new completed status.
	 */
	public void setIsCompleted(int newIsCompleted) {
		String sqlstmt = "UPDATE task SET iscompleted = " + newIsCompleted + " WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the ObjectList associated with an existing task.
	 * @param newObjectList ObjectList containing the new list of associated objects
	 */
	public void setObjectList(ObjectList newObjectList) {
		String sqlstmt = "UPDATE task SET objectlist = " + newObjectList.asString() + " WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the creator of an existing task.
	 * @param newCreator New User to be set as the creator.
	 */
	public void setCreator(User newCreator) {
		String sqlstmt = "UPDATE task SET creator = " + newCreator.getUserName() + " WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}

	/**
	 * Used to edit the user a task is assigned to.
	 * @param newAssignedTo New User for the task to be assigned to.
	 */
	public void setAssignedTo(User newAssignedTo) {
		String sqlstmt = "UPDATE task SET assignedto = " + newAssignedTo.getUserName() + " WHERE name = " + name;
		GUI guiInst = GUI.getInstance();
		guiInst.databaseUpdate(sqlstmt);
	}
}
