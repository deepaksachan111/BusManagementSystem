package com.quaere.noida.busmanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.quaere.noida.busmanagementsystem.database.AddBusData;
import com.quaere.noida.busmanagementsystem.database.DatabaseHandler;
import com.quaere.noida.busmanagementsystem.database.Member;
import com.quaere.noida.busmanagementsystem.database.SearchData;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {



    private EditText edt_source;
    private EditText edt_destination;
    private Button search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        edt_source = (EditText) findViewById(R.id.edt_source_search);
        edt_destination = (EditText) findViewById(R.id.edt_destination_search);
        search = (Button) findViewById(R.id.btn_search_search);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getsource = edt_source.getText().toString();
                String getdestination = edt_destination.getText().toString();


                // Creating Bundle object
                Bundle b = new Bundle();
                // Storing data into bundle
                b.putString("source", getsource);
                b.putString("destination", getdestination);
             ;

                // Creating Intent object
                Intent in = new Intent(SearchActivity.this,SearchResultActivity.class);
                // Storing bundle object into intent
                in.putExtras(b);
                startActivity(in);

              //  startActivity(new Intent(SearchActivity.this,SearchResultActivity.class));

            }
        });

    }

}