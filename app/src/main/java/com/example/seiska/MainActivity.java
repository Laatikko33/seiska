package com.example.seiska;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public class Listener extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"hmmm", Toast.LENGTH_LONG).show();
            Date aika = Calendar.getInstance().getTime();
            MyEntity myEntity = new MyEntity();
            if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                myEntity.aika = aika.toString();
                myEntity.tapahtuma = "Screen off";
            }
            else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                myEntity.aika = aika.toString();
                myEntity.tapahtuma = "Screen on";
            }
            Intent intent1 = new Intent(MainActivity.this, Intent.class);
            intent1.putExtra("asdasd",myEntity);
            context.startService(intent1);
        }
    }
    Listener listener;
    Database database;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> stringArraylist;
    MyTableDao myTableDao;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listView = findViewById(R.id.listView1);
        stringArraylist = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArraylist);
        listView.setAdapter(arrayAdapter);
        database = Room.databaseBuilder(getApplicationContext(), Database.class,Database.NIMI).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        myTableDao = database.myTableDao();
        listener = new Listener();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(listener, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<MyEntity> list = myTableDao.Descending();
        for(MyEntity m : list){
            String string = "";
            string = string + m.tapahtuma + m.tapahtuma;
            arrayAdapter.add(string);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(listener);
    }
}
