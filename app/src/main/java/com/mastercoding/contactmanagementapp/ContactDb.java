package com.mastercoding.contactmanagementapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//no of entities class should be mention here
@Database(entities = {Contact.class}, version = 1)
public abstract class ContactDb extends RoomDatabase {
    public abstract ContactDAO getContactDAO();

    //Singleton Pattern :- ensures a class has only one instance
    // and provides a global point of access to that instance

    private static ContactDb dbInstance;

    public static synchronized ContactDb getDbInstance(Context context) {
        //need to check any existing instance of the db is
        // there or not during the lifecycle of the app

        //fallbackToDestructMigration():- is a method used in the context of Android Room database.
        // It is a mechanism that tells Room to wipe
        // out and recreate the database if there is a mismatch between the database version
        // and the schema version
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDb.class, "contacts_db").fallbackToDestructiveMigration().build();

        }
        return dbInstance;
    }
}
