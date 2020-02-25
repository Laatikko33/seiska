package com.example.seiska;

import android.app.IntentService;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;
import androidx.room.Room;


public class Intent extends IntentService {


    public Intent(String name) {
        super(name);

    }

    MyTableDao myTableDao;

    @Override
    protected void onHandleIntent(@Nullable android.content.Intent intent) {
        Database database = Room.databaseBuilder(getApplicationContext(),
                Database.class, Database.NIMI).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        this.myTableDao = database.myTableDao();

        MyEntity myEntity = (MyEntity) intent.getSerializableExtra("asdasd");
        myTableDao.InsertEntity(myEntity);
    }
}
