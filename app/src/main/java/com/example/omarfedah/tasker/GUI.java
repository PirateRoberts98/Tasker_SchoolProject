package com.example.omarfedah.tasker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Robert on 2017-11-21.
 */

public class GUI {

	String databaseURL;
	User activeUser;


	private Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(databaseURL);
			return conn;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		}

	return null;}
/*
TAKEN OUT SO I CAN TEST UI (FEDAH)
	private ResultSet databaseQuery(String sqlstmt) {
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.execute(sqlstmt);
		return rs;
	}

	private void databaseUpdate(String sqlstmt) {
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sqlstmt);
	}

*/
    public TaskList getAllTasks() {
    	//SELECT all tasks from task table
    	//create new com.example.omarfedah.tasker.TaskList Object
    	//return com.example.omarfedah.tasker.TaskList
		return null;}

    public ObjectList getAllObjects() {
    	//SELECT all objects from object table\
    	//create new com.example.omarfedah.tasker.ObjectList Object
    	//return com.example.omarfedah.tasker.ObjectList
		return null;}

    public ObjectList getShoppingList() {
    	//SELECT all objects WHERE isowned = false
    	//create new com.example.omarfedah.tasker.ObjectList Object
    	//return com.example.omarfedah.tasker.ObjectList
		return null;}

    public TaskList getUserTasks(String username) {
    	//SELECT all tasks WHERE name = username
    	//create new com.example.omarfedah.tasker.TaskList Object
    	//return com.example.omarfedah.tasker.TaskList
    return null;}

    public void addTask(GUI RAWSAUSE) {
    	//interpret UI inputs
    	//call com.example.omarfedah.tasker.Task constructor method
    }

    public void removeTask(String name) {
    	//remove task from task table
    }

    public void addObject(String name, boolean isGrocery, boolean isOwned) {
    	//call com.example.omarfedah.tasker.PurchasableObject constructor method
    }

    public void removeObject(String name) {
    	//remove object from object table
    }

    public void addUser(GUI RAWSAUSE) {
    	//interpret UI inputs
    	//call com.example.omarfedah.tasker.User constructor method
    }

    public void removeUser(String name) {
    	//remove user from user table
    }

}
