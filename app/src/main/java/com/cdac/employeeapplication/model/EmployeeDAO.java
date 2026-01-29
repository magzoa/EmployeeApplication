package com.cdac.employeeapplication.model;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDAO {

    @Insert
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

    @Query("DELETE FROM employees")
    void deleteAllEmployees();

    @Query("SELECT * FROM employees ORDER BY name ASC")
    LiveData<List<Employee>> getAllEmployees();
}
