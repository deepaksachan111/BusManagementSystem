package com.quaere.noida.busmanagementsystem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quaere.noida.busmanagementsystem.database.AddBusData;
import com.quaere.noida.busmanagementsystem.database.DatabaseHandler;

public class AddBusActivity extends Activity {
 private EditText edt_busno,edt_source,edt_destination,edt_arrival,edt_departure,edt_fare;
    private Button btn_addbus;
    private String busno,source,destination,arrival,departure,fare;
     DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        db = new DatabaseHandler(this);

        edt_busno=(EditText)findViewById(R.id.edt_bus_no);
        edt_source=(EditText)findViewById(R.id.edt_source);
        edt_destination=(EditText)findViewById(R.id.edt_destination);
        edt_arrival=(EditText)findViewById(R.id.edt_arrival);
        edt_departure=(EditText)findViewById(R.id.edt_departure);
        edt_fare=(EditText)findViewById(R.id.edt_fare);
        btn_addbus=(Button)findViewById(R.id.btn_addbus);
        btn_addbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBusrecords();
            }
        });

    }

    private void addBusrecords() {
        busno = edt_busno.getText().toString();
        source = edt_source.getText().toString();
        destination = edt_destination.getText().toString();
        arrival = edt_arrival.getText().toString();
        departure = edt_departure.getText().toString();
        fare = edt_fare.getText().toString();

        if (busno.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Bus No", Toast.LENGTH_SHORT)
                    .show();
            edt_busno.requestFocus();
        } else if (source.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Source", Toast.LENGTH_SHORT).show();
            edt_source.requestFocus();
        } else if (destination.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Destination", Toast.LENGTH_SHORT).show();
            edt_destination.requestFocus();
        } else if (arrival.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter arrival", Toast.LENGTH_SHORT)
                    .show();
            edt_arrival.requestFocus();
        } else {

            // Inserting Contacts
            Log.d("Insert: ", "Inserting ..");
            db.addnewBus(new AddBusData(busno, source, destination, arrival, departure, fare));

         /*   // Reading all contacts
            Log.d("Reading: ", "Reading all contacts..");
            List<Member> contacts = db.getAllContacts();
            for (Member cn : contacts) {
                String log = "Name: " + cn.getName() + " Email,: " + cn.getEmail() + " ,Phone: " + cn.getMobileNo() + " , password: " + cn.getPswd();
                // Writing Contacts to log
                Log.d("Name: ", log);
                 }*/

              Toast.makeText(AddBusActivity.this,"filled record Successfully",Toast.LENGTH_LONG).show();
          //  startActivity(new Intent(AddBusActivity.this, SearchActivity.class));
            finish();

        }

    }


}
