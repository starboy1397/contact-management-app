package com.mastercoding.contactmanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.mastercoding.contactmanagementapp.databinding.ContactListBinding;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.contactViewHolder> {

    private ArrayList<Contact> contactArrayList;

    public ContactAdapter(ArrayList<Contact> contactArrayList) {
        this.contactArrayList = contactArrayList;
    }

    @NonNull
    @Override
    public contactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Creating new View holders for items in the recycler view
        ContactListBinding contactListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contact_list,
                parent,
                false
        );
        return new contactViewHolder(contactListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull contactViewHolder holder, int position) {
        // called by recyclerview when it needed to display or update an item.
        // at a specific position in the list or grid
        Contact currentContact = contactArrayList.get(position);
        holder.contactListBinding.setContact(currentContact);

    }

    @Override
    public int getItemCount() {
        //determines the total of items in the dataset that will
        // be displayed in the recycler view.
        if (contactArrayList != null) {
            return contactArrayList.size();
        }else {
        return 0;
        }
    }

    public void setContactArrayList(ArrayList<Contact> contactArrayList) {
        this.contactArrayList = contactArrayList;
        //informs the recycler view that the dataset has changed
        // and the recycler view should update its contents.
        notifyDataSetChanged();
    }

    class contactViewHolder extends RecyclerView.ViewHolder{

        //databinding with recylerview
        private ContactListBinding contactListBinding;

        public contactViewHolder(@NonNull ContactListBinding contactListBinding) {
            super(contactListBinding.getRoot());
            this.contactListBinding = contactListBinding;
        }
    }
}
