package com.mastercoding.contactmanagementapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {
    Contact contact;
    Context context;
    ContactViewModel contactViewModel;

    public AddNewContactClickHandler(Contact contact, Context context, ContactViewModel contactViewModel) {
        this.contact = contact;
        this.context = context;
        this.contactViewModel = contactViewModel;
    }

    public void onSubmitBtnClick(View view){
        if (contact.getName() == null || contact.getEmail() == null){
            Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(context, MainActivity.class);
//            intent.putExtra("Name", contact.getName());
//            intent.putExtra("Email", contact.getEmail());
            Contact c = new Contact(contact.getName(), contact.getEmail());
            contactViewModel.insertContact(c);
            context.startActivity(intent);
        }
    }
}
