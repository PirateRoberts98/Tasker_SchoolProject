package com.example.omarfedah.tasker;

import java.sql.ResultSet;
import java.sql.SQLException;

//missing proper imports
//requires external methods databaseQuery(String), databaseUpdate(String), getUser(String), com.example.omarfedah.tasker.ObjectList.asString()
 class Task extends Collectable {

	private String name;
	//database int endDateTime;
	//database boolean isCompleted;
	//database String note;
	//database com.example.omarfedah.tasker.ObjectList com.example.omarfedah.tasker.ObjectList;
	//database com.example.omarfedah.tasker.User creator
	//database com.example.omarfedah.tasker.User assignedTo

	public Task(String name, int endDateTime, boolean isCompleted, String note, ObjectList objectList, User creator, User assignedTo) {
		this.name = name;
		String values = "VALUES (" + name + "," + endDateTime + "," + isCompleted + "," + note + "," + objectList.asString() + "," + creator.getUserName() + "," + assignedTo.getUserName() + ")";
		String sqlstmt = "INSERT INTO task(name,enddatetime,iscompleted,note,objectlist,creator,assignedto) " + values;
		GUI.databaseUpdate(sqlstmt);
	}

	public Task(String name) {
		this.name = name;
	}

	public String getTaskName() {
		return name;
	}

	public int getEndDateTime() {
		String sqlstmt = "SELECT enddatetime FROM task WHERE name = " + name;
		ResultSet rs = GUI.databaseQuery(sqlstmt);
		try {
		 int endDateTime = rs.getInt("enddatetime");
         return endDateTime;
        } catch (SQLException e) {
		 return -1;
        }
	}

	public boolean getIsCompleted() {
		String sqlstmt = "SELECT iscompleted FROM task WHERE name = " + name;
		ResultSet rs = GUI.databaseQuery(sqlstmt);
		try {
         boolean isCompleted = rs.getBoolean("iscompleted");
         return isCompleted;
        } catch (SQLException e) {
		 //this is bad,
		 return false;
        }
	}

	public ObjectList getObjectList() {
		String sqlstmt = "SELECT objectlist FROM task WHERE name = " + name;
		ResultSet rs = GUI.databaseQuery(sqlstmt);
		try {
         String objectListString = rs.getString("objectlist");
         String[] objects = objectListString.split("/");
         ObjectList objectList = new ObjectList();
         for (String obj : objects) {
          objectList.add(new PurchasableObject(obj));
         }
         return objectList;
        } catch (SQLException e) {
		 return new ObjectList();
        }
	}

	public User getCreator() {
		String sqlstmt = "SELECT creator FROM task WHERE name = " + name;
		ResultSet rs = GUI.databaseQuery(sqlstmt);
		try {
         String creatorName = rs.getString("creator");
         User creator = new User(creatorName);
         return creator;
        } catch (SQLException e) {
		 return new User("");
        }
	}

	public User getAssignedTo() {
		String sqlstmt = "SELECT assignedto FROM task WHERE name = " + name;
		ResultSet rs = GUI.databaseQuery(sqlstmt);
		try {
         String assignedToName = rs.getString("assignedto");
         User assignedTo = new User(assignedToName);
         return assignedTo;
        } catch (SQLException e) {
		 return new User("");
        }
	}

	public void setTaskName(String newName) {
		String sqlstmt = "UPDATE task SET name = " + newName + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
		this.name = newName;
	}

	public void setEndDateTime(int newEndDateTime) {
		String sqlstmt = "UPDATE task SET enddatetime = " + newEndDateTime + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}

	public void setIsCompleted(boolean newIsCompleted) {
		String sqlstmt = "UPDATE task SET iscompleted = " + newIsCompleted + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}

	public void setObjectList(ObjectList newObjectList) {
		String sqlstmt = "UPDATE task SET objectlist = " + newObjectList.asString() + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}

	public void setCreator(User newCreator) {
		String sqlstmt = "UPDATE task SET creator = " + newCreator.getUserName() + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}

	public void setAssignedTo(User newAssignedTo) {
		String sqlstmt = "UPDATE task SET assignedto = " + newAssignedTo.getUserName() + " WHERE name = " + name;
		GUI.databaseUpdate(sqlstmt);
	}

}
