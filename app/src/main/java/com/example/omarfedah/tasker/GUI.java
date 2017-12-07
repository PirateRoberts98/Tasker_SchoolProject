package com.example.omarfedah.tasker;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import java.io.File;
import java.util.ArrayList;

/**
 * Singletion class for connecting to the database.
 */
public class GUI {
	private static final String DATABASE_PATH = "app/assets/Tasker.sqlite3"; //Path to the database file
	public static User activeUser; //User who is currently logged in and using the app
	private static GUI singleInstance = null; //Reference to the single instance of GUI

	/**
	 * Creates a new instance of GUI.
	 */
	private GUI() {
	}

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
		QueryResult qr = databaseQuery(sqlstmt);
		TaskList allTasks = new TaskList();
		Cursor rs = qr.getResultSet();

		while (rs.moveToNext()) {
			String taskName = rs.getString(0);
			allTasks.add(new Task(taskName));
		}

		qr.close();

		return allTasks;
	}

	/**
	 * Queries the database to generate a TaskList object containing all tasks assigned
	 * to a specified user.
	 * @param userName String containing the specified user's name.
	 * @return TaskList object containing all tasks assigned to a specified user.
	 */
	public TaskList getUserTasks(String userName) {
		String sqlstmt = "SELECT name FROM task WHERE assignedto = " + "'" + userName + "'";
		QueryResult qr = databaseQuery(sqlstmt);
		TaskList userTasks = new TaskList();
		Cursor rs = qr.getResultSet();

		while (rs.moveToNext()) {
			String taskName = rs.getString(0);
			userTasks.add(new Task(taskName));
		}

		qr.close();

		return userTasks;
	}

	/**
	 * Queries the database to generate a UserList object containing all users that exist in the
	 * database.
	 * @return UserList object containing all existing Users.
	 */
	public UserList getAllUser() {
		String sqlstmt = "SELECT name FROM user";
		QueryResult qr = databaseQuery(sqlstmt);
		UserList allUsers = new UserList();
		Cursor rs = qr.getResultSet();

		while (rs.moveToNext()) {
			String userName = rs.getString(0);
			allUsers.add(new User(userName));
		}

		qr.close();

		return allUsers;
	}

	/**
	 * Queries the database to generate a TaskList objects containing all tasks marked as completed.
	 * @return TaskList object containing all tasks marked as completed.
	 */
	public TaskList getCompletedTasks() {
		String sqlstmt = "SELECT name FROM task WHERE iscompleted = 1";
		QueryResult qr = databaseQuery(sqlstmt);
		TaskList completedTasks = new TaskList();
		Cursor rs = qr.getResultSet();

		while (rs.moveToNext()) {
			String taskName = rs.getString(0);
			completedTasks.add(new Task(taskName));
		}
		qr.close();

		return completedTasks;
	}

	/**
	 * Calls the Task constructor to add a new task to the database.
	 * @param taskName String containing the task name.
	 * @param endDateTime Long representation of the date and time, formatted as YYYMMDDHHmmL.
	 * @param isCompleted Boolean representation of the task's completed status.
	 * @param note String containing any notes associated to the task.
	 * @param objectList ObjectList containing any PurchasableObjects associated to the task.
	 * @param creatorUser User that created the task.
	 * @param assignedUser User that the task is assigned to.
	 */
    public Task addTask(String taskName, long endDateTime, Boolean isCompleted, String note,
						ObjectList objectList, User creatorUser, User assignedUser) throws UniqueIDException {
		return new Task(taskName, endDateTime, isCompleted, note, objectList, creatorUser, assignedUser);
    }

	/**
	 * Edits the attributes of an instance of Task.
	 * @param editedTask Instance of task to edit.
	 * @param taskName Name for the Task.
	 * @param endDateTime Long representation of the date and time, formatted as YYYMMDDHHmmL.
	 * @param isCompleted Task completion status.
	 * @param assignedUser User assigned to the task.
	 */
	public void editTask(Task editedTask,String taskName, long endDateTime, boolean isCompleted,
						User assignedUser) throws UniqueIDException{
    	editedTask.setTaskName(taskName);
    	editedTask.setEndDateTime(endDateTime);
		editedTask.setIsCompleted(isCompleted);
    	editedTask.setAssignedTo(assignedUser);
	}

	/**
	 * Removes an existing task from the database.
	 * @param name String containing the name of the task to be removed.
	 */
    public void removeTask(String name) {
		String sqlstmt = "DELETE FROM task WHERE name = " + "'" + name + "'";
		databaseUpdate(sqlstmt);
    }

	/**
	 * Calls the PurchasableObject constructor to add a new PurchasableObject to the
	 * database.
	 * @param name String containing the PurchasableObject name.
	 * @param isGrocery  Boolean representation of the PurchasableObject's 'isGrocery' status.
	 * @param isOwned Boolean representation of the PurchasableObject's 'isOwned' status.
	 */
    public PurchasableObject addObject(String name, Boolean isGrocery, Boolean isOwned) throws UniqueIDException {
		return new PurchasableObject(name, isGrocery, isOwned);
    }

	/**
	 * Removes an existing PurchasableObject from the database.
	 * @param name String containing the PurchasableObject name.
	 */
	public void removeObject(String name) {
		String sqlstmt = "DELETE FROM object WHERE name = " + "'" + name + "'";
		databaseUpdate(sqlstmt);
    }

	/**
	 * Calls the User constructor to add a new User to the database.
	 * @param name String containing the user name.
	 * @param icon String representation of the user's selected icon.
	 * @param password String containing the user's password.
	 */
    public User addUser(String name, String icon, String password) throws UniqueIDException {
		return new User(name, icon, password);
	}

	/**
	 * Removes an existing user from the database.
	 * @param name String containing the name of the user to be removed.
	 */
    public void removeUser(String name) {
		String sqlstmt = "DELETE FROM user WHERE name = " + "'" + name + "'";
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
		String sqlstsmt = "SELECT password FROM user WHERE name = " + "'" + user.getUserName() + "'";
		QueryResult qr = databaseQuery(sqlstsmt);
		Cursor rs = qr.getResultSet();
		rs.moveToFirst();
		String storedPassword = rs.getString(0);
		qr.close();

		return (password.equals(storedPassword));
	}

	/**
	 * Queried the database to generate an ObjectList containing all existing
	 * PurchasableObjects.
	 * @return ObjectList containing all existing PurchasableObjects.
	 */
	public ObjectList getAllObjects() {
		String sqlstmt = "SELECT name FROM object";
		QueryResult qr = databaseQuery(sqlstmt);
		ObjectList allObjects = new ObjectList();
		Cursor rs = qr.getResultSet();

		while (rs.moveToNext()) {
			String objectName = rs.getString(0);
			allObjects.add(new PurchasableObject(objectName));
		}

		qr.close();

		return allObjects;
	}

	/**
	 * Queries the database to generate an ObjectList containing all existing
	 * PurchasableObjects who's 'isOwned' status is false.
	 * @return ObjectList containing all existing unowned PurchasableObjects.
	 */
	public ObjectList getObjectList(boolean isGrocery, boolean isOwned) {
		String sqlstmt = "SELECT name FROM object WHERE isgrocery = " + (isGrocery ? 1 : 0) + " AND isowned = " + (isOwned ? 1 : 0);
		QueryResult qr = databaseQuery(sqlstmt);
		Cursor rs = qr.getResultSet();
		ObjectList objectList = new ObjectList();

		while (rs.moveToNext()) {
			String objectName = rs.getString(0);
			objectList.add(new PurchasableObject(objectName));
		}

		qr.close();

		return objectList;
	}

	/**
	 * Getter method for the currently active user.
	 * @return User Current user.
	 */
	public User getActiveUser() {
		return activeUser;
	}

	/**
	 * Setter method for setting the currently active user.
	 * @param user New current user.
	 */
	public void setActiveUser(User user) {
		activeUser = user;
	}

	/**
	 * Method used to establish a connection the the SQLite database.
	 * @return Connection instance associated to specified SQLite database.
	 */
	public SQLiteDatabase connect() {
		SQLiteDatabase conn;
		String dbPath = HomeActivity.getAppContext().getDatabasePath("Tasker").getPath();
		File dbFile = new File(dbPath);
		conn = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);

		return conn;
	}

	/**
	 * Executes the specified SQL statement and returns any results as a Cursor object.
	 * Used to query the database.
	 * @param sqlstmt String containing the SQL statement to be executed.
	 * @return Cursor containing all results from the query.
	 */
	public QueryResult databaseQuery(String sqlstmt) {
		SQLiteDatabase conn = connect();
		Cursor rs = conn.rawQuery(sqlstmt, null);
		QueryResult qr = new QueryResult(rs, conn);

		return qr;
	}

	/**
	 * Executes a specified SQL statement. Used to update of delete entries in the database.
	 * @param sqlstmt String containing the SQL statement to be executed.
	 */
	public void databaseUpdate(String sqlstmt) {
		SQLiteDatabase conn = connect();
		conn.execSQL(sqlstmt);
		conn.close();
	}

	/**
	 * Checks whether or not an entry already exists in the database.
	 * @param uniqueID Entry to check for in the database.
	 * @param tableName Table to query.
	 * @throws UniqueIDException
	 */
	public void checkUniqueID(String uniqueID, String tableName) throws UniqueIDException {
		boolean isUniqueID = true;

		//Check if the task name is unique
		if (tableName.equals("task")) {
		 	ArrayList<Task> allTasks = getAllTasks().getList();
		 	for (Task task : allTasks) {
		 		if (task.getTaskName().equals(uniqueID)) {
					isUniqueID = false;
				}
			}
        }

        //Check if the user name is unique
        if (tableName.equals("user")) {
		 	ArrayList<User> allUsers = getAllUser().getList();
		 	for (User user : allUsers) {
		 		if (user.getUserName().equals(uniqueID)) {
		 			isUniqueID = false;
				}
			}
		}

		//Check if the object name is unique
		if (tableName.equals("object")) {
		 	ArrayList<PurchasableObject> allObjects = getAllObjects().getList();
		 	for (PurchasableObject object : allObjects) {
		 		if (object.getObjectName().equals(uniqueID)) {
		 			isUniqueID = false;
				}
			}
		}

		//Throw a UniqueIDException based on the outcome of the search.
		if (!isUniqueID) {
			throw new UniqueIDException();
		}
    }
}
