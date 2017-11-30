package com.example.omarfedah.tasker;

import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import android.database.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class GUI {
//Attributes

	private static final String DATABASE_PATH = "app/src/main/res/database/Tasker.sqlite3";
	public static User activeUser;
	private static GUI singleInstance = null;

	/**
	 * Creates a new instance of GUI.
	 */
	private GUI() {
	}

//Public Methods

	/**
	 * Creates a new instance of GUI if one doesn't already exist.
	 * @return The singleton instance of GUI.
	 */
	public static GUI getInstance() {
		if (singleInstance == null) {
			singleInstance = new GUI();
		}

		return singleInstance;
	}
	/**
	 * Queries the database to generate a TaskList object containing all existing tasks.
	 * @return TaskList object containing all existing tasks.
	 */
	public TaskList getAllTasks() {
		String sqlstmt = "SELECT name FROM task";
		Cursor rs = databaseQuery(sqlstmt);
		TaskList allTasks = new TaskList();
		while (rs.moveToNext()) {
			String taskName = rs.getString(0);
			allTasks.add(new Task(taskName));
		} return allTasks;
	}

	/**
	 * Queries the database to generate a TaskList object containing all tasks assigned
	 * to a specified user.
	 * @param userName String containing the specified user's name.
	 * @return TaskList object containing all tasks assigned to a specified user.
	 */
	public TaskList getUserTasks(String userName) {
		String sqlstmt = "SELECT name FROM task WHERE assignedto = " + userName;
		Cursor rs = databaseQuery(sqlstmt);
		TaskList userTasks = new TaskList();
		while (rs.moveToNext()) {
			String taskName = rs.getString(0);
			userTasks.add(new Task(taskName));
		} return userTasks;
	}

	/**
	 * Calls the Task constructor to add a new task to the database.
	 * @param taskName String containing the task name.
	 * @param endDateTime Integer representation of the date and time, formatted as
	 *                    YYYMMDDHHmm.
	 * @param isCompleted int Boolean representation of the task's completed status.
	 * @param note String containing any notes associated to the task.
	 * @param objectList ObjectList containing any PurchasableObjects associated to the task.
	 * @param creatorUser User that created the task.
	 * @param assignedUser User that the task is assigned to.
	 */
    public void addTask(String taskName, int endDateTime, int isCompleted, String note,
						ObjectList objectList, User creatorUser, User assignedUser) {
		new Task(taskName, endDateTime, isCompleted, note, objectList, creatorUser, assignedUser);
    }

	/**
	 * Edits the attributes of an instance of Task.
	 * @param editedTask Instance of task to edit.
	 * @param taskName Name for the Task.
	 * @param endDateTime End date and time for the Task.
	 * @param isCompleted Task completion status.
	 * @param assignedUser User assigned to the task.
	 */
	public void editTask(Task editedTask,String taskName, int endDateTime, boolean isCompleted,
						User assignedUser) {
    	editedTask.setTaskName(taskName);
    	editedTask.setEndDateTime(endDateTime);
  //COMMENTED AS CODE ERROR
		// 	editedTask.setIsCompleted(isCompleted);
    	editedTask.setAssignedTo(assignedUser);
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
	 * @param isGrocery int Boolean representation of the PurchasableObject's 'isGrocery' status.
	 * @param isOwned int Boolean representation of the PurchasableObject's 'isOwned' status.
	 */
    public void addObject(String name, int isGrocery, int isOwned) {
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
		String sqlstsmt = "SELECT password FROM user WHERE name = " + user.getUserName();
		Cursor rs = databaseQuery(sqlstsmt);
		String storedPassword = rs.getString(2);
		return (password.equals(storedPassword));
	}

	/**
	 * Queried the database to generate an ObjectList containing all existing
	 * PurchasableObjects.
	 * @return ObjectList containing all existing PurchasableObjects.
	 */
	public ObjectList getAllObjects() {
		String sqlstmt = "SELECT name FROM object";
		Cursor rs = databaseQuery(sqlstmt);
		ObjectList allObjects = new ObjectList();
		while (rs.moveToNext()) {
			String objectName = rs.getString(0);
			allObjects.add(new PurchasableObject(objectName));
		} return allObjects;
	}

	/**
	 * Queries the database to generate an ObjectList containing all existing
	 * PurchasableObjects who's 'isOwned' status is false.
	 * @return ObjectList containing all existing unowned PurchasableObjects.
	 */
	public ObjectList getShoppingList() {
		String sqlstmt = "SELECT name FROM object WHERE isowned = false";
		Cursor rs = databaseQuery(sqlstmt);
		ObjectList shoppingList = new ObjectList();
		while (rs.moveToNext()) {
			String objectName = rs.getString(0);
			shoppingList.add(new PurchasableObject(objectName));
		} return shoppingList;
	}


// Private methods for internal use

	/**
	 * Method used to establish a connection the the SQLite database.
	 * @return Connection instance associated to specified SQLite database.
	 */
	public SQLiteDatabase connect() {
		SQLiteDatabase conn;
		String dbPath = HomeActivity.getAppContext().getDatabasePath("Tasker").getPath();
		conn = SQLiteDatabase.openDatabase(dbPath, null,
				SQLiteDatabase.OPEN_READWRITE);
		Toast.makeText(HomeActivity.getAppContext(), "Connected to Database", Toast.LENGTH_SHORT).show();
		return conn;
	}

	/**
	 * Executes the specified SQL statement and returns any results as a Cursor object.
	 * Used to query the databse.
	 * @param sqlstmt String containing the SQL statement to be executed.
	 * @return Cursor containing all results from the query.
	 */
	public Cursor databaseQuery(String sqlstmt) {
		SQLiteDatabase conn = connect();
		Cursor rs = conn.rawQuery(sqlstmt, null);
		conn.close();
		Toast.makeText(HomeActivity.getAppContext(), "Queried the Database", Toast.LENGTH_SHORT).show();
		return rs;
	}

	/**
	 * Executes a specified SQL statement. Used to update of delete entries in the database.
	 * @param sqlstmt String containing the SQL statement to be executed.
	 */
	public void databaseUpdate(String sqlstmt) {
		SQLiteDatabase conn = connect();
		conn.execSQL(sqlstmt);
		conn.close();
		Toast.makeText(HomeActivity.getAppContext(), "Updated the Database", Toast.LENGTH_SHORT).show();

	}
}
