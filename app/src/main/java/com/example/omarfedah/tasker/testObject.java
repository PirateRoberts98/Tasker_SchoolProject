package com.example.omarfedah.tasker;

//requires external methods databaseQuery(String), databaseUpdate(String)

public class testObject extends Collectable  {

        private String name;
        protected boolean isGrocery;
        protected boolean isOwned;

        /**
         * Constructor for PurchasableObject object. Adds the newly created PurchasableObject to
         * the database and returns an instance of the new PurchasableObject.
         * @param name String containing the name of the PurchasableObject
         * @param isGrocery int Boolean representation of whether the PurchasableObject is groceries.
         * @param isOwned int Boolean representation of whether the PurchasableObject is owned.
         */
        public testObject(String name, boolean isGrocery, boolean isOwned) {
            this.name = name;
            this.isGrocery = isGrocery ;
            this.isOwned = isOwned ;
        }

        /**
         * Secondary constructor for PurchasableObject object. Returns an instance of the new
         * PurchasableObject without adding it to the database.
         * @param name String containing the name of the PurchasableObject.
         */
        public testObject(String name) {
            this.name = name;
        }

        /**
         * Getter for the PurchasableObject name.
         * @return String containing the PurchasableObject name.
         */
        public String getObjectName() {
            return name;
        }

        /**
         * Getter for PurchasableObject's isGrocery status.
         * @return int Boolean representation of whether the PurchasableObject is groceries.
         */
        public boolean getIsGrocery() {
           return isGrocery ;
        }

        /**
         * Getter for PurchasableObject's isOwned status.
         * @return int Boolean representation of whether the PurchasableObject is owned.
         */
        public boolean getIsOwned() {
            return isOwned ;
        }

        /**
         * Used to edit the name of an existing PurchasableObject.
         * @param newName String containing the PurchasableObject's new name.
         */
        public void setObjectName(String newName) {
            name = newName ;
        }

        /**
         * Used to edit the 'isGrocery' status of an existing PurchasableObject.
         * @param NewIsGrocery int Boolean representation of the PurchasableObject's new 'isGrocery'
         *                     status.
         */
        public void setIsGrocery(boolean NewIsGrocery) {
            isGrocery = NewIsGrocery  ;
        }

        /**
         * Used to edit the 'isOwned' status of an existing PurchasableObject.
         * @param NewIsOwned int Boolean representation of the PurchasableObject's new 'isOwned'
         *                   status.
         */
        public void setIsOwned(boolean NewIsOwned) {
            isOwned = NewIsOwned ;
        }

        @Override
        public String toString() {
            return name ;
        }
}


