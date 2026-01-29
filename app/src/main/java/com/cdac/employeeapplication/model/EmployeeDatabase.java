package com.cdac.employeeapplication.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Employee.class},version = 1)
public abstract class EmployeeDatabase  extends RoomDatabase {


    private static EmployeeDatabase instance;
    public abstract EmployeeDAO employeeDAO();

    public static synchronized EmployeeDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),
                            EmployeeDatabase.class, "employee_database")

                    .build();
        }
        return instance;
    }



}
