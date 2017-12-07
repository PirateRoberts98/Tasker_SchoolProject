package com.example.omarfedah.tasker;

//NOTE: Can we use boolean for iscomplete and convert from database on call?

public class testTASK  extends Collectable {

        private String name;
        private int endDateTime;
        private boolean isCompleted;
        private String note;
        private ObjectList objectList;
        private User creator  ;
        private User assignedTo  ;

        /**
         * Constructor for Task object. Adds the newly created task to the database and returns
         * an instance of the new Task object.
         * @param name String containing the task name.
         * @param endDateTime Integer representing the end date and time of the task, formatted
         *                    as YYYYMMDDHHmm. Uses 24 hour time.
         * @param isCompleted int Boolean for whether or the task has been completed.
         * @param note String containing a note associated to the task.
         * @param objectList ObjectList containing any objects associated to the task.
         * @param creator User that created the task.
         * @param assignedTo User the task is assigned to.
         */
        public testTASK(String name, int endDateTime, boolean isCompleted, String note, ObjectList objectList, User creator, User assignedTo) {
            this.name = name;
            this.endDateTime = endDateTime ;
            this.isCompleted = isCompleted ;
            this.note = note ;
            this.objectList = null ;
            this.creator = creator ;
            this.assignedTo = assignedTo ;
        }

        /**
         * Secondary constructor for Task object. Returns an instance of a new Task object but
         * does not create a new entry in the database.
         * @param name String containing the task name.
         */
        public testTASK(String name) {
            this.name = name;
        }

        /**
         * Getter for task name.
         * @return String containing the task name.
         */
        public String getTaskName() {
            return name;
        }

        /**
         * Getter for task end date and time
         * @return Integer representation of datetime, formatted as YYYYMMDDHHmm.
         */
        public int getEndDateTime() {
            return endDateTime ;
        }

        /**
         * Getter for task completed status.
         * @return int Boolean representing task completed status.
         */
        public boolean getIsCompleted() {
            return isCompleted ;
        }

        /**
         * Getter for a task's associated ObjectList.
         * @return ObjectList containing all onjects associated with the task.
         */
        public ObjectList getObjectList() {
            return objectList;
        }

        /**
         * Getter for the User who created the task.
         * @return User who created the task.
         */
        public User getCreator() {
                return creator ;
        }

        /**
         * Getter for User the task is assigned to.
         * @return User the task is assigned to.
         */
        public User getAssignedTo() {
            return assignedTo ;
        }

        /**
         * Used to edit the name of an existing task.
         * @param newName String containing the new task name.
         */
        public void setTaskName(String newName) {
            name = newName ;
        }

        /**
         * Used to edit the end date and time of an existing task.
         * @param newEndDateTime Integer representing the new end date and time, formatted as
         *                       YYYYMMDDHHmm.
         */
        public void setEndDateTime(int newEndDateTime) {
            endDateTime = newEndDateTime ;
        }

        /**
         * Used to edit the completed status of an existing task.
         * @param newIsCompleted int Boolean representing the new completed status.
         */
        public void setIsCompleted(boolean newIsCompleted) {
        isCompleted = newIsCompleted ;
        }

        /**
         * Used to edit the ObjectList associated with an existing task.
         * @param newObjectList ObjectList containing the new list of associated objects
         */
        public void setObjectList(ObjectList newObjectList) {
            objectList = newObjectList ;
        }

        /**
         * Used to edit the creator of an existing task.
         * @param newCreator New User to be set as the creator.
         */
        public void setCreator(User newCreator) {
            creator = newCreator ;
        }

        /**
         * Used to edit the user a task is assigned to.
         * @param newAssignedTo New User for the task to be assigned to.
         */
        public void setAssignedTo(User newAssignedTo) {
            assignedTo = newAssignedTo ;
        }

    @Override
    public String toString() {
        return name ;
    }
}


