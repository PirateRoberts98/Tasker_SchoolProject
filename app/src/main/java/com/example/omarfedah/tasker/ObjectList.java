package com.example.omarfedah.tasker;



class ObjectList extends List<PurchasableObject> {

    public ObjectList() {
        super();
    }

    /**
     * Creates one string containing the names of all objects in an ObjectList, formatted
     * for storage in the database.
     * @return String containing all object names
     */
    public String asString() {
        String asString = "";
        for (PurchasableObject obj: getList()) {
            asString = asString + obj.getObjectName() + "/";
        }
        if (!asString.equals("") && asString.length() > 0) {
            asString = asString.substring(0, asString.length() - 1);
        }
        return asString;
    }
}
