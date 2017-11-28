package com.example.omarfedah.tasker;

import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


public class GUI {
//Attributes

	public static String databaseURL;
	static User activeUser;

//Public Methods

	/**
	 * Queries the database to generate a TaskList object containing all existing tasks.
	 * @return TaskList object containing all existing tasks.
	 */
	public TaskList getAllTasks() {
		String sqlstmt = "SELECT name FROM task";
		ResultSet rs = databaseQuery(sqlstmt);
		TaskList allTasks = new TaskList();
		try {
			while (rs.next()) {
				String taskName = rs.getString("name");
				allTasks.add(new Task(taskName));
			}
		} catch (SQLException e) {
			return null;
		}
		return allTasks;
	}

	// to be removed
	public String[] getTaskNames(){
		//PLEASE PUT IMPLEMENTATION HERE
	return null ;
	}

	// to be removed
	public String[] getUserNames(){
		//PLEASE PUT IMPLEMENTATION HERE
		return null ;
	}

	/**
	 * Queries the database to generate a TaskList object containing all tasks assigned
	 * to a specified user.
	 * @param userName String containing the specified user's name.
	 * @return TaskList object containing all tasks assigned to a specified user.
	 */
	public TaskList getUserTasks(String userName) {
		String sqlstmt = "SELECT name FROM task WHERE assignedto = " + userName;
		ResultSet rs = databaseQuery(sqlstmt);
		TaskList userTasks = new TaskList();
		try {
			while (rs.next()) {
				String taskName = rs.getString("name");
				userTasks.add(new Task(taskName));
			}
		} catch (SQLException e) {
			return null;
		}
		return userTasks;
	}

	/**
	 * Calls the Task constructor to add a new task to the database.
	 * @param taskName String containing the task name.
	 * @param endDateTime Integer representation of the date and time, formatted as
	 *                    YYYMMDDHHmm.
	 * @param isCompleted Boolean representation of the task's completed status.
	 * @param note String containing any notes associated to the task.
	 * @param objectList ObjectList containing any PurchasableObjects associated to the task.
	 * @param creatorUser User that created the task.
	 * @param assignedUser User that the task is assigned to.
	 */
    public void addTask(String taskName, int endDateTime, boolean isCompleted, String note,
						ObjectList objectList, User creatorUser, User assignedUser) {
		new Task(taskName, endDateTime, isCompleted, note, objectList, creatorUser, assignedUser);
    }

    // we probably really don't need this
	public void editTask(Task editedTask,String taskName, int date , int time, boolean isCompleted,
						User assignedUser, User createdUser) {
		//interpret UI inputs
		//call com.example.omarfedah.tasker.Task constructor method
	}

	/**
	 * Removes an existing task from the database.
	 * @param name String containing the name of the task to be removed.
	 */
    public void removeTask(String name) {
		String sqlstmt = "DELETE FROM task WHERE name = " + name;
		databaseUpdate(sqlstmt);
    }

	/**
	 * Calls the PurchasableObject constructor to add a new PurchasableObject to the
	 * database.
	 * @param name String containing the PurchasableObject name.
	 * @param isGrocery Boolean representation of the PurchasableObject's 'isGrocery' status.
	 * @param isOwned Boolean representation of the PurchasableObject's 'isOwned' status.
	 */
    public void addObject(String name, boolean isGrocery, boolean isOwned) {
		new PurchasableObject(name, isGrocery, isOwned);
    }

	/**
	 * Removes an existing PurchasableObject from the database.
	 * @param name String containing the PurchasableObject name.
	 */
	public void removeObject(String name) {
		String sqlstmt = "DELETE FROM object WHERE name = " + name;
		databaseUpdate(sqlstmt);
    }
	 //not sure what this is
    public void addUser(GUI RAWSAUSE) {
    	//interpret UI inputs
    	//call com.example.omarfedah.tasker.User constructor method
    }

	/**
	 * Calls the User constructor to add a new User to the database.
	 * @param name String containing the user name.
	 * @param icon String representation of the user's selected icon.
	 * @param password String containing the user's password.
	 */
    public void addUser(String name, String icon, String password) {
		new User(name, icon, password);
	}

	/**
	 * Removes an existing user from the database.
	 * @param name String containing the name of the user to be removed.
	 */
    public void removeUser(String name) {
		String sqlstmt = "DELTE FROM user WHERE name = " + name;
		databaseUpdate(sqlstmt);
    }

	/**
	 * Verifies that the inputted password matches the User's password that's stored in the
	 *  database.
	 * @param user User who's password must be authenticated.
	 * @param password String containing the password input by the user.
	 * @return True iff the input password matches the stored password.
	 */
    public boolean authenticateUser(User user, String password){
		//Please Implement code here
		String sqlstsmt = "SELECT password FROM user WHERE name = " + user.getUserName();
		ResultSet rs = databaseQuery(sqlstsmt);
		try {
			String storedPassword = rs.getString("password");
			return (password.equals(storedPassword));
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Queried the database to generate an ObjectList containing all existing
	 * PurchasableObjects.
	 * @return ObjectList containing all existing PurchasableObjects.
	 */
	public ObjectList getAllObjects() {
		String sqlstmt = "SELECT name FROM object";
		ResultSet rs = databaseQuery(sqlstmt);
		ObjectList allObjects = new ObjectList();
		try {
			while (rs.next()) {
				String objectName = rs.getString("name");
				allObjects.add(new PurchasableObject(objectName));
			}
		} catch (SQLException e) {
			return null;
		}
		return allObjects;
	}

	/**
	 * Queries the database to generate an ObjectList containing all existing
	 * PurchasableObjects who's 'isOwned' status is false.
	 * @return ObjectList containing all existing unowned PurchasableObjects.
	 */
	public ObjectList getShoppingList() {
		String sqlstmt = "SELECT name FROM object WHERE isowned = false";
		ResultSet rs = databaseQuery(sqlstmt);
		ObjectList shoppingList = new ObjectList();
		try {
			while (rs.next()) {
				String objectName = rs.getString("name");
				shoppingList.add(new PurchasableObject(objectName));
			}
		} catch (SQLException e) {
			return null;
		}
		return shoppingList;
	}


// Private methods for internal use

	/**
	 * Method used to establish a connection the the SQLite database.
	 * @return Connection instance associated to specified SQLite database.
	 */
	public static Connection connect() {
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

		} return null;

	}

	/**
	 * Executes the specified SQL statement and returns any results as a ResultSet object.
	 * Used to query the databse.
	 * @param sqlstmt String containing the SQL statement to be executed.
	 * @return ResultSet containing all results from the query.
	 */
	public static ResultSet databaseQuery(String sqlstmt) {
		Connection conn = connect();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlstmt);
			return rs;
		} catch (SQLException e) {

/*			Toast errorMessage = Toast.makeText(, e.getMessage(), 10);
			errorMessage.show();*/
		} finally {
			try {
				conn.close();
			} catch (SQLException e){
/*				Toast errorMessage = Toast.makeText(HomeActivity.this, e.getMessage(), 10);
				errorMessage.show();*/
			}
		} return null;
	}

	/**
	 * Executes a specified SQL statement. Used to update of delete entries in the database.
	 * @param sqlstmt String containing the SQL statement to be executed.
	 */
	public static void databaseUpdate(String sqlstmt) {
		Connection conn = connect();
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sqlstmt);
		} catch (SQLException e) {
/*			Toast errorMessage = Toast.makeText(HomeActivity.this, e.getMessage(), 10);
			errorMessage.show();*/
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
/*					Toast errorMessage = Toast.makeText(HomeActivity.this, e.getMessage(), 10);
					errorMessage.show();*/
				}
			}
		}
	}
}
