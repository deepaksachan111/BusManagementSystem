package com.quaere.noida.busmanagementsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intex on 4/14/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "My";

    // Contacts table name
    private static final String TABLE_CONTACTS = "no_of_people";
    private static final String TABLE_ADDBUS = "add_bus";

    // Contacts Table Columns names

    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_PSWD = "pswd";

    // Contacts Table Columns names for add Bus

    private static final String KEY_BUSNAME = "busname";
    private static final String KEY_SOURCE = "source";
    private static final String KEY_DESTINATION = "destination";
    private static final String KEY_ARRIVAL = "arrival";
    private static final String KEY_DEPARTURE = "departure";
    private static final String KEY_FARE = "fare";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_NAME + " TEXT PRIMARY KEY," + KEY_PH_NO + " TEXT,"
             + KEY_PSWD + " TEXT" + ")";


        String CREATE_TABLE_ADDBUS = "CREATE TABLE " + TABLE_ADDBUS + "("
                + KEY_BUSNAME + " TEXT PRIMARY KEY," + KEY_SOURCE + " TEXT,"
                + KEY_DESTINATION + " TEXT," +  KEY_ARRIVAL + " TEXT," +    KEY_DEPARTURE + " TEXT,"  + KEY_FARE + " TEXT" + ")";

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_ADDBUS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDBUS);
        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addContact(Member contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getMobileNo()); // Contact Phone// Contact Name
        values.put(KEY_PSWD, contact.getPswd()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    // code to add the new addBus
    public void addnewBus(AddBusData busData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BUSNAME, busData.getBusname());
        values.put(KEY_SOURCE, busData.getSource());
        values.put(KEY_DESTINATION, busData.getDestination());
        values.put(KEY_ARRIVAL, busData.getArrival());
        values.put(KEY_DEPARTURE, busData.getDeparture());
        values.put(KEY_FARE, busData.getFare());


        // Inserting Row
        db.insert(TABLE_ADDBUS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }


    // code to get the single contact
    Member getContact(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_NAME,KEY_PH_NO,
                         KEY_PSWD}, KEY_NAME + "=?", new String[]{name}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Member contact = new Member((cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return contact;
    }

    // code to get the single AddBus

    public void deleteSingleRow(String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ADDBUS + " WHERE " + KEY_BUSNAME + "='" + value + "'");
        db.close();
    }


    public List<String> showData(String u,String p)
    {
        SQLiteDatabase db =this.getReadableDatabase();

        List<String> list = new ArrayList<String>();

        String str="No Data Found";
        String query = "SELECT * FROM "+TABLE_ADDBUS+" WHERE source=? and destination=?";
        Cursor cursor = db.rawQuery(query, new String[]{u,p});

        int c = cursor.getCount();

        if(c > 0)
        {
            while(cursor.moveToNext())
            {
                str = ""+cursor.getString(0)+":"+cursor.getString(1)+":"+cursor.getString(2)+"";
                list.add(str);
            }
        }
        return list;
    }

   public   List< AddBusData> getAddBus(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
       List<AddBusData> buslistList = new ArrayList<AddBusData>();

       Cursor cursor = db.query(TABLE_ADDBUS, new String[]{KEY_BUSNAME,KEY_SOURCE,KEY_DESTINATION,
                KEY_ARRIVAL,KEY_DEPARTURE,KEY_FARE}, KEY_SOURCE + "=?", new String[]{name}, null, null,null);



           if (cursor.moveToFirst()) {
               do {
                   AddBusData addBusData = new AddBusData();
                   addBusData.setBusname(cursor.getString(0));
                   addBusData.setSource(cursor.getString(1));
                   addBusData.setDestination(cursor.getString(2));
                   addBusData.setArrival(cursor.getString(3));
                   addBusData.setDeparture(cursor.getString(4));
                   addBusData.setFare(cursor.getString(5));
                   buslistList.add(addBusData);
           // return contact

               } while (cursor.moveToNext());
           }
           return buslistList;


    }

    // code to get all contacts in a list view
    public List<Member> getAllContacts() {
        List<Member> contactList = new ArrayList<Member>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Member contact = new Member();
                //contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(0));
                contact.setMobileNo(cursor.getString(1));
                contact.setPswd(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }


    public List<AddBusData> getAllAddBusData() {
        List<AddBusData> buslistList = new ArrayList<AddBusData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ADDBUS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddBusData addBusData = new AddBusData();
                //contact.setID(Integer.parseInt(cursor.getString(0)));
                addBusData.setBusname(cursor.getString(0));
                addBusData.setSource(cursor.getString(1));
                addBusData.setDestination(cursor.getString(2));
                addBusData.setArrival(cursor.getString(3));
                addBusData.setDeparture(cursor.getString(4));
                addBusData.setFare(cursor.getString(5));
                // Adding contact to list
                buslistList.add(addBusData);
            } while (cursor.moveToNext());
        }
        // return contact list
        return buslistList;
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Getting AddBusData Count
    public int getAddBusCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ADDBUS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
