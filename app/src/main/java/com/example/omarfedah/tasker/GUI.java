package com.example.omarfedah.tasker;

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

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

	}

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


    public TaskList getAllTasks() {
    	//SELECT all tasks from task table
    	//create new TaskList Object 
    	//return TaskList
    }

    public ObjectList getAllObjects() {
    	//SELECT all objects from object table\
    	//create new ObjectList Object
    	//return ObjectList
    }

    public ObjectList getShoppingList() {
    	//SELECT all objects WHERE isowned = false
    	//create new ObjectList Object
    	//return ObjectList
    }

    public TaskList getUserTasks(String username) {
    	//SELECT all tasks WHERE name = username
    	//create new TaskList Object
    	//return TaskList
    }

    public void addTask(?) {
    	//interpret UI inputs
    	//call Task constructor method
    }

    public void removeTask(String name) {
    	//remove task from task table
    }

    public void addObject(String name, boolean isGrocery, boolean isOwned) {
    	//call PurchasableObject constructor method
    }

    public void removeObject(String name) {
    	//remove object from object table
    }

    public void addUser(?) {
    	//interpret UI inputs
    	//call User constructor method
    }

    public void removeUser(String name) {
    	//remove user from user table
    }
}
