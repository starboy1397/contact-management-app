package com.mastercoding.contactmanagementapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repository {
    Executor executor;
    Handler handler;

    // the available data source
    // -Room database
    // if we have only one data source we don't need repository.
    // as we dont have coroutines in java we have to use
    // handler and executor to run the db in background not in main thread.
    public Repository(Application application) {

        ContactDb contactDatabase = ContactDb.getDbInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();
        //used for executing the db operation in background thread
        executor = Executors.newSingleThreadExecutor();
        // used for updating the UI in main thread
        handler = new Handler(Looper.getMainLooper());
    }

    private final ContactDAO contactDAO;

    // Methods in DAO being executed from repository
    public void insertContact(Contact contact) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //execute this code asynchronous on separate thread
                contactDAO.insertContact(contact); // this is running in background thread
            }
        });
    }
    public void deleteContact(Contact contact) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.deleteContact(contact);
            }
        });

    }
    //live data can be used in repository as well as DAO
    //in repository live data is typically used to expose data from
    // the new db to ViewModel and ultimately to the ui components
    public LiveData<List<Contact>> getAllContacts() {
        return contactDAO.getAllContacts();
    }
}
