package com.example.omarfedah.tasker;



class ObjectList extends List<PurchasableObject> {

    public ObjectList() {
        super();
    }

    public String asString() {
        String asString = "";
        for (PurchasableObject obj: getList()) {
            asString = asString + obj.getObjectName();
        } return asString;
    }
}
