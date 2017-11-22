/**
 * Created by Robert on 2017-11-22.
 */

//missing proper imports
//requires external methods databaseQuery(String), databaseUpdate(String)

public class PurchasableObject implements Collectable {

	private String name;
	//database boolean isGrocery;
	//database boolean isOwned;

	public PurchasableObject(String name, boolean isGrocery, boolean isOwned) {
		this.name = name;
		String values = "VALUES (" + name + "," + isGrocery + "," + isOwned + ")";
		String sqlstmt = "INSERT INTO object(name, isGrocery, isOwned) " + values;
		databaseUpdate(sqlstmt);
	}

	public PurchasableObject(String name) {
		this.name = name;
	}

	public String getObjectName() {
		return name;
	}

	public boolean getIsGrocery() {
		String sqlstmt = "SELECT isgrocery FROM object WHERE name = " + name;
		ResultSet rs = databaseQuery(sqlstmt);
		boolean isGrocery = rs.getBoolean("isgrocery");
		return isGrocery;
	}

	public boolean getIsOwned() {
		String sqlstmt = "SELECT isowned FROM object WHERE name = " + name;
		ResultSet rs = databaseQuery(sqlstmt);
		boolean isOwned = rs.getBoolean("isowned");
		return isOwned;
	}

	public void setObjectName(String newName) {
		String sqlstmt = "UPDATE object SET name = " + newName + " WHERE name = " + name;
		databaseUpdate(sqlstmt);
		this.name = newName;
	}

	public void setIsGrocery(boolean NewIsGrocery) {
		String sqlstmt = "UPDATE object SET isgrocery = " + NewIsGrocery + " WHERE name = " + name;
		databaseUpdate(sqlstmt);
	}

	public void setIsOwned(boolean NewIsOwned) {
		String sqlstmt = "UPDATE object SET isowned = " + NewIsOwned + " WHERE name = " + name;
		databaseUpdate(sqlstmt);
	}
}
