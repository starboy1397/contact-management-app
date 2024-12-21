package com.mastercoding.contactmanagementapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    void insertContact(Contact contact);

    @Delete
    void deleteContact(Contact contact);

    //select all from contacts_table
    @Query("SELECT * FROM contacts_table")
    LiveData<List<Contact>> getAllContacts();
}
