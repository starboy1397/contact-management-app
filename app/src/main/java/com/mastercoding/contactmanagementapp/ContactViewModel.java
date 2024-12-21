package com.mastercoding.contactmanagementapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    // if you need to use context inside your ViewModel
    // you should use AndroidViewModel(AVM) instead of ViewModel
    // because it contains the application context.

    private Repository contactRepository;

    // LiveData
    private LiveData<List<Contact>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        this.contactRepository = new Repository(application);
    }

    public LiveData<List<Contact>> getAllContacts() {
        allContacts = contactRepository.getAllContacts();
        return allContacts;
    }

    public void insertContact(Contact contact) {
        contactRepository.insertContact(contact);
    }
    public void deleteContact(Contact contact) {
        contactRepository.deleteContact(contact);
    }

    // AndroidViewModel class is a subclass of ViewModel
    // and similar to them, they are designed to store and
    // manage UI-related data are responsible to
    // prepare & provide data for UI and automatically
    // allow data to survive configuration change.
}
