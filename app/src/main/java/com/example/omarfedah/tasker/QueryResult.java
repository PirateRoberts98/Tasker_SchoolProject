package com.example.omarfedah.tasker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by phil_ on 2017-12-03.
 */

public class QueryResult {
    private Cursor resultSet;
    private SQLiteDatabase conn;

    /**
     * Constructor for ResultSet.
     * @param resultSet Cursor containing all the queried entries.
     * @param conn SQLiteDatabase object that is being queried. This should always have an open connection.
     */
    public QueryResult(Cursor resultSet, SQLiteDatabase conn) {
        this.resultSet = resultSet;
        this.conn = conn;
    }

    /**
     * Getter for the queried result set.
     * @return Cursor object containing the queried entries.
     */
    public Cursor getResultSet() {
        return resultSet;
    }

    /**
     * Getter for the associated database connection.
     * @return SQLiteDatabase object with an open connection.
     */
    public SQLiteDatabase getDatabaseConnection() {
        return conn;
    }

    /**
     * Method to close both the Cursor connection and the SQLiteDatabase connection.
     */
    public void close() {
        resultSet.close();
        conn.close();
    }
}
