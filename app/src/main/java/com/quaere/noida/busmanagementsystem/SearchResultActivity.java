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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.quaere.noida.busmanagementsystem.database.AddBusData;
import com.quaere.noida.busmanagementsystem.database.DatabaseHandler;
import com.quaere.noida.busmanagementsystem.database.SearchData;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends Activity {
    DatabaseHandler db;
    private ListView listView;
    private AdapterA adaptera;
    private ArrayList<AddBusData> getsearchList = new ArrayList<>();

    private String getsource, getdestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        db = new DatabaseHandler(this);
        listView = (ListView) findViewById(R.id.listview_search);
        final View header = (View) getLayoutInflater().inflate(R.layout.listview_header_row_search, null);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        getsource = bundle.getString("source");
        getdestination = bundle.getString("destination");

        searchresult();


        adaptera = new AdapterA(SearchResultActivity.this, R.layout.listview_search, getsearchList);
        listView.addHeaderView(header);
        listView.setAdapter(adaptera);
    }

    private void searchresult() {
      /*  String ddd ="";


        String datas = db.showData(getsource, getdestination).toString();

        String string = datas;
        String[] parts = string.split(":");
        String part1 = parts[0]; // 004
        String part2 = parts[1];
        String part3 = parts[2];// 034556
        String replacedString = part3.replace("]", "");

        if(!parts.equals("")){
            getsearchList.add(new SearchData(part2,replacedString));
        }else {
            Toast.makeText(this, "Norecord found", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this,datas,Toast.LENGTH_LONG).show();*/

      //  List<AddBusData> datas = db.getAllAddBusData();

//
          List  <AddBusData> data=  db.getAddBus(getsource);

        if(data.size() >0){
            for (AddBusData datas : data){
                String busno = datas.getBusname();
                String source = datas.getSource();
                String desitnatin = datas.getDestination();
                String arrival = datas.getArrival();
                String depature = datas.getDeparture();
                String fare = datas.getFare();
                getsearchList.add(new AddBusData(busno,source, desitnatin,arrival,depature,fare));
            }
        }
       else {

            Toast.makeText(SearchResultActivity.this,"No Record Found",Toast.LENGTH_LONG).show();
        }



   /*     for (AddBusData cn : datas) {
            // String log = " Phone: " + cn.getEmail() + " , password: " + cn.getPswd();
            String busno = cn.getBusname();
            String source = cn.getSource();
            String desitnatin = cn.getDestination();
            String arrival = cn.getArrival();
            String depature = cn.getDeparture();
            String fare = cn.getFare();

            getsearchList.add(new AddBusData(busno,source, desitnatin,arrival,depature,fare));
            // Writing Contacts to log
            // Log.d("Name: ", log);


        }*/


    }

}

class AdapterA extends ArrayAdapter {

    Context context;
    int layoutResourceId;
    ArrayList<AddBusData> data;

    public AdapterA(Context context, int layoutResourceId, ArrayList<AddBusData> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new WeatherHolder();
            holder.txt_busno =(TextView)row.findViewById(R.id.lst_busname_adapter);
            holder.txt_sorce = (TextView) row.findViewById(R.id.lst_source_adapter);
            holder.txt_destination = (TextView) row.findViewById(R.id.lst_destination_adapter);
            holder.txt_arrival = (TextView) row.findViewById(R.id.lst_arrival_adapter);
            holder.txt_depature = (TextView) row.findViewById(R.id.lst_departure_adapter333);
            holder.txt_fare = (TextView) row.findViewById(R.id.lst_fare_adapter333);

            row.setTag(holder);
        } else {
            holder = (WeatherHolder) row.getTag();
        }

        AddBusData searchData = data.get(position);
        holder.txt_busno.setText(searchData.getBusname());
        holder.txt_sorce.setText(searchData.getSource());
        holder.txt_destination.setText(searchData.getDestination());
        holder.txt_arrival.setText(searchData.getArrival());
        holder.txt_depature.setText(searchData.getDeparture());
        holder.txt_fare.setText(searchData.getFare());

        return row;
    }

    static class WeatherHolder {
        TextView txt_busno;
        TextView txt_sorce;
        TextView txt_destination;
        TextView txt_arrival;
        TextView txt_depature;
        TextView txt_fare;
    }
}