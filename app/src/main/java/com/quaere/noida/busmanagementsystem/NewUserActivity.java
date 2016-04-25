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
import android.widget.TextView;
import android.widget.Toast;

import com.quaere.noida.busmanagementsystem.database.DatabaseHandler;
import com.quaere.noida.busmanagementsystem.database.Member;

public class NewUserActivity extends Activity {
    private Button btn_register;
    private EditText edt_fullname,edt_mobile, edt_password;
    private TextView tv_already;
    DatabaseHandler db;
    private String name ,mobile,email,pswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
      edt_fullname =(EditText)findViewById(R.id.edt_fullname);
        edt_mobile=(EditText)findViewById(R.id.edt_mobileno);
        edt_password =(EditText)findViewById(R.id.edt_register_password);
        btn_register =(Button)findViewById(R.id.btn_register);
        tv_already =(TextView)findViewById(R.id.tv_alraedylogin);
        db = new DatabaseHandler(this);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singUp();
            }
        });

        tv_already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void singUp() {
        name = edt_fullname.getText().toString();
        mobile = edt_mobile.getText().toString();
        pswd = edt_password.getText().toString();
        if (name.equals("") && mobile.equals("")  && pswd.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter All Fields",
                    Toast.LENGTH_SHORT).show();
            edt_fullname.requestFocus();
        } else if (name.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter name", Toast.LENGTH_SHORT)
                    .show();
            edt_fullname.requestFocus();
        }else if (mobile.length() < 10) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Correct Mobile No", Toast.LENGTH_SHORT)
                    .show();
            edt_mobile.requestFocus();
        }
        else if (pswd.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Password", Toast.LENGTH_SHORT).show();
            edt_password.requestFocus();

        } else {

            // Inserting Contacts
            Log.d("Insert: ", "Inserting ..");
            db.addContact(new Member(name, mobile,  pswd));

         /*   // Reading all contacts
            Log.d("Reading: ", "Reading all contacts..");
            List<Member> contacts = db.getAllContacts();
            for (Member cn : contacts) {
                String log = "Name: " + cn.getName() + " Email,: " + cn.getEmail() + " ,Phone: " + cn.getMobileNo() + " , password: " + cn.getPswd();
                // Writing Contacts to log
                Log.d("Name: ", log);
                 }*/

            startActivity(new Intent(NewUserActivity.this, MainActivity.class));

        }

    }

}
