package com.quaere.noida.busmanagementsystem;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.quaere.noida.busmanagementsystem.database.DatabaseHandler;

public class DeleteActivity extends Activity {
  DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

          db = new DatabaseHandler(this);

           final EditText editText =(EditText) findViewById(R.id.edt_bus_no_delete);
          Button  button =(Button)findViewById(R.id.btn_delete_bus);

             button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                   String delete =  editText.getText().toString();
                     db.deleteSingleRow(delete);
                 }
             });

    }


}
