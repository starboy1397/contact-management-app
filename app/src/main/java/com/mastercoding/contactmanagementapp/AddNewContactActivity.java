package com.mastercoding.contactmanagementapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.mastercoding.contactmanagementapp.databinding.ActivityAddNewContactBinding;

public class AddNewContactActivity extends AppCompatActivity {

    private ActivityAddNewContactBinding binding;
    private AddNewContactClickHandler addNewContactClickHandler;
    private Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        contact = new Contact();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_contact);
        binding.setContact(contact);

        ContactViewModel contactViewModel = new ViewModelProvider(this)
                .get(ContactViewModel.class);


        addNewContactClickHandler = new AddNewContactClickHandler(contact, this, contactViewModel);
        binding.setClickHandler(addNewContactClickHandler);

    }
}