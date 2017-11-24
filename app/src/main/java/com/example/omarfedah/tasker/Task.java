package com.example.omarfedah.tasker;

/**
 * Created by Robert on 2017-11-22.
 */

//missing proper imports
//requires external methods databaseQuery(String), databaseUpdate(String), getUser(String), com.example.omarfedah.tasker.ObjectList.asString()

public class Task implements Collectable {
/*
	private String name;
	//database Timestamp endDateTime;
	//database boolean isCompleted;
	//database String note;
	//database com.example.omarfedah.tasker.ObjectList com.example.omarfedah.tasker.ObjectList;
	//database com.example.omarfedah.tasker.User creator
	//database com.example.omarfedah.tasker.User assignedTo

	public Task(String name, Timestamp endDateTime, boolean isCompleted, String note, ObjectList objectList, User creator, User assignedTo) {
		this.name = name;
		String values = "VALUES (" + name + "," + endDateTime + "," + iscompleted + "," + note + "," + objectList.asString() + "," + creator.getUser() + "," + assignedTo.getUser() + ")";
		String sqlstmt = "INSERT INTO task(name,enddatetime,iscompleted,note,objectlist,creator,assignedto) " + values;
		databaseUpdate(sqlstmt);
	}

	public Task(String name) {
		this.name = name;
	}

	public String getTaskName() {
		return name;
	}

	public Timestamp getEndDateTime() {
		String sqlstmt = "SELECT enddatetime FROM task WHERE name = " + name;
		ResultSet rs = databaseQuery(sqlstmt);
		Timestamp endDateTime = rs.getTimestamp("enddatetime");
		return endDateTime;
	}

	public boolean getIsCompleted() {
		String sqlstmt = "SELECT iscompleted FROM task WHERE name = " + name;
		ResultSet rs = databaseQuery(sqlstmt);
		boolean isCompleted = rs.getBoolean("iscompleted");
		return isCompleted;
	}

	public ObjectList getObjectList() {
		String sqlstmt = "SELECT objectlist FROM task WHERE name = " + name;
		ResultSet rs = databaseQuery(sqlstmt);
		String objectListString = rs.getString("objectlist");
		String[] objects = objectListString.split("/");
		ObjectList objectList = new ObjectList();
		for (String obj : objects) {
			objectList.add(new PurchasableObject(obj));
		}
		return objectList;
	}

	public User getCreator() {
		String sqlstmt = "SELECT creator FROM task WHERE name = " + name;
		ResultSet rs = databaseQuery(sqlstmt);
		String creatorName = rs.getString("creator");
		User creator = getUser(creatorName);
		return creator;
	}

	public User getAssignedTo() {
		String sqlstmt = "SELECT assignedto FROM task WHERE name = " + name;
		ResultSet rs = databaseQuery(sqlstmt);
		String assignedToName = rs.getString("assignedto");
		User assignedTo = getUser(assignedToName);
		return assignedTo;
	}

	public void setTaskName(String newName) {
		String sqlstmt = "UPDATE task SET name = " + newName + " WHERE name = " + name;
		databaseUpdate(sqlstmt);
		this.name = newName;
	}

	public void setEndDateTime(DateTime newEndDateTime) {
		String sqlstmt = "UPDATE task SET enddatetime = " + newEndDateTime + " WHERE name = " + name;
		databaseUpdate(sqlstmt);
	}

	public void setIsCompleted(boolean newIsCompleted) {
		String sqlstmt = "UPDATE task SET iscompleted = " + newIsCompleted + " WHERE name = " + name;
		databaseUpdate(sqlstmt);
	}

	public void setObjectList(ObjectList newObjectList) {
		String sqlstmt = "UPDATE task SET objectlist = " + newObjectList.asString() + " WHERE name = " + name;
		databaseUpdate(sqlstmt);
	}

	public void setCreator(User newCreator) {
		String sqlstmt = "UPDATE task SET creator = " + newCreator.getUser() + " WHERE name = " + name;
		databaseUpdate(sqlstmt);
	}

	public void setAssignedTo(User newAssignedTo) {
		String sqlstmt = "UPDATE task SET assignedto = " + newAssignedTo.getUser() + " WHERE name = " + name;
		databaseUpdate(sqlstmt);
	}
*/
}
