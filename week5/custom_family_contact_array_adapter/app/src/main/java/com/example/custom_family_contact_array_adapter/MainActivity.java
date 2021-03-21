package com.example.custom_family_contact_array_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TASK 1: CREATE AN ARRAY OF CONTACT OBJECTS.
        //EACH CONTACT OBJECT CONSISTS OF A NAME, RELATIONSHIP, AND A PHOTO

        ArrayList<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("Mom", "mother", R.drawable.female1));
        contacts.add(new Contact("Dad", "father", R.drawable.male1));
        contacts.add(new Contact("Amanda", "sister", R.drawable.female2));
        contacts.add(new Contact("Grandma", "grandma", R.drawable.female1));
        contacts.add(new Contact("Grandpa", "grandpa", R.drawable.male1));
        contacts.add(new Contact("Joey", "friend", R.drawable.male2));
        contacts.add(new Contact("Cameron", "friend", R.drawable.male3));
        contacts.add(new Contact("Donovan", "friend", R.drawable.male4));
        contacts.add(new Contact("Koby", "friend", R.drawable.male2));
        contacts.add(new Contact("Reyn", "friend", R.drawable.male2));
        contacts.add(new Contact("Rayden", "friend", R.drawable.male1));
        contacts.add(new Contact("Thomas", "friend", R.drawable.male1));
        contacts.add(new Contact("Riley", "friend", R.drawable.male2));
        contacts.add(new Contact("Marissa", "friend", R.drawable.female1));

        // A CUSTOM ARRAY ADAPTER TO LIST CONTACTS IN A CUSTOM LIST VIEW
        ContactAdapter contactAdapter = new ContactAdapter(this, contacts);

        // Find the ListView object in the view hierarchy of the Activity.
        ListView listView = (ListView) findViewById(R.id.listview);

        // Make the listView object use the ContactAdapter created above.
        listView.setAdapter(contactAdapter);

    }
}