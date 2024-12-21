package com.mastercoding.contactmanagementapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mastercoding.contactmanagementapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // Data source
        private ContactDb contactDb;
        private ArrayList<Contact> contactArrayList = new ArrayList<>();
    // Adapter
        private ContactAdapter contactAdapter;
    // binding
        private ActivityMainBinding mainBinding;
        private MainActivityClickHandler clickHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        clickHandler = new MainActivityClickHandler(this);
        mainBinding.setClickHandler(clickHandler);

        //Recycler View
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        // Database
        contactDb = ContactDb.getDbInstance(this);

        // ViewModel
        //this line of code is used to get and create and retrieve an instance of the ViewModel class
        // associated with specific activity or fragment.
        ContactViewModel contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        // Inserting a new contact (just for testing)
//        Contact c1 = new Contact("Ravi", "ravinarayan3346@gmail.com");
//        contactViewModel.insertContact(c1);
        //Loading the data from RoomDb
        contactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                contactArrayList.clear();
                for (Contact c : contacts) {
                    Log.v("MainActivity", c.getName());
                    contactArrayList.add(c);
                }
                contactAdapter.notifyDataSetChanged();
            }
        });
        // it deal with the viewmodel and the viewmodel deal with the repository
        // and the repository deal with the room database this is how MVVM works.
        // Adapter
        contactAdapter = new ContactAdapter(contactArrayList);
        //linking the recycler view with adapter
        recyclerView.setAdapter(contactAdapter);

        //swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                //for drag and drop operation
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                //if you swipe item to left
                Contact c = contactArrayList.get(viewHolder.getAdapterPosition());
                contactViewModel.deleteContact(c);
            }
        }).attachToRecyclerView(recyclerView);
    }
}