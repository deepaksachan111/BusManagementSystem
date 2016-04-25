package com.quaere.noida.busmanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quaere.noida.busmanagementsystem.database.DatabaseHandler;
import com.quaere.noida.busmanagementsystem.database.Member;

import java.util.List;

public class MainActivity extends Activity {
    private Button btn_login;
    private EditText edt_name,edt_password;
    private TextView tv_sinup;
    private String name,password;

    DatabaseHandler db;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_name =(EditText)findViewById(R.id.edt_loginname);
        edt_password=(EditText)findViewById(R.id.edt_loginpassword);
        btn_login =(Button)findViewById(R.id.btn_login);
        tv_sinup =(TextView)findViewById(R.id.tv_sinup);
           db= new DatabaseHandler(this);
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        /*if (sharedpreferences.getString("logged", "").toString().equals("logged")) {

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("phone", sharedpreferences.getString("phone", ""));
            startActivity(intent);
            finish();

        }*/

         btn_login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                String getname = edt_name.getText().toString().trim();
                 String getpassword = edt_password.getText().toString().trim();

                 if(getname.equalsIgnoreCase("admin")&& getpassword.equals("admin")){
                     startActivity(new Intent(MainActivity.this, HomeActivity.class));
                     finish();
                 }
                 else{
                     signIn();
                     //Toast.makeText(MainActivity.this,"Invalid username or password",Toast.LENGTH_LONG).show();

                 }
             }
         });

        tv_sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewUserActivity.class));
            }
        });
    }

    private void signIn() {
        name = edt_name.getText().toString().trim();
        password = edt_password.getText().toString().trim();

        if (name.equals("") && password.equals("")) {

            Toast.makeText(getApplicationContext(),
                    "Please enter Mobile No and Password",
                    Toast.LENGTH_SHORT).show();
            edt_name.requestFocus();
        } else if (name.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Mobile No", Toast.LENGTH_SHORT)
                    .show();
            edt_name.requestFocus();
        } else if (password.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please enter Password", Toast.LENGTH_SHORT).show();
            edt_password.requestFocus();

        } else {
            List<Member> contacts = db.getAllContacts();
            for (Member cn : contacts) {
                // String log = " Phone: " + cn.getEmail() + " , password: " + cn.getPswd();
              String  phone = cn.getMobileNo();
              String  pass = cn.getPswd();
                // Writing Contacts to log
                // Log.d("Name: ", log);
                if (name.equals(phone) && pass.equals(pass)) {
                    editor = sharedpreferences.edit();
                    editor.putString("phone", phone);
                    editor.putString("pswd", pass);
                    editor.putString("logged", "logged");
                    editor.commit();

                    startActivity(new Intent(MainActivity.this, SearchActivity.class));
                    this.finish();
                }
                else{
                    // startActivity(new Intent(SingInActivity.this, MainPage.class));
                    Toast.makeText(getApplicationContext(),"Enter Correct Mobile No and Password",Toast.LENGTH_SHORT).show();
                }

            }
        }

    }
}
