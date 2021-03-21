package com.example.family_contacts_array_adapter;

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
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        contactList.add(new Contact("Arthur Weasley", "father", R.drawable.male1));
        contactList.add(new Contact("Molly Weasley", "mother", R.drawable.female1));
        contactList.add(new Contact("Bill Weasley", "brother", R.drawable.male2));
        contactList.add(new Contact("Charlie Weasley", "brother", R.drawable.male3));
        contactList.add(new Contact("Fred Weasley", "brother", R.drawable.male4));
        contactList.add(new Contact("George Weasley", "brother", R.drawable.male3));
        contactList.add(new Contact("Percy Weasley", "brother", R.drawable.male2));
        contactList.add(new Contact("Ginny Weasley", "sister", R.drawable.female2));
        contactList.add(new Contact("Ron Weasley", "brother", R.drawable.male1));
        contactList.add(new Contact("Morris Weasley", "brother", R.drawable.male4));
        contactList.add(new Contact("Sadie Weasley", "brother", R.drawable.female3));
        contactList.add(new Contact("Anne Weasley", "brother", R.drawable.female2));
        contactList.add(new Contact("Jon Weasley", "sister", R.drawable.male2));
        contactList.add(new Contact("Kris Weasley", "brother", R.drawable.male1));

        // A CUSTOM ARRAY ADAPTER TO LIST CONTACTS IN A CUSTOM LIST VIEW
        ContactAdapter contactAdapter = new ContactAdapter(this, contactList);

        // Find the ListView object in the view hierarchy of the Activity.
        ListView listView = (ListView) findViewById(R.id.listview);

        // Make the listView object use the ContactAdapter created above.
        listView.setAdapter(contactAdapter);

    }
}

