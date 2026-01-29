package com.cdac.employeeapplication.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmployeeRepository {


    private final EmployeeDAO employeeDAO;
    private final LiveData<List<Employee>> allEmployees;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public EmployeeRepository(Application application) {
        EmployeeDatabase database = EmployeeDatabase.getInstance(application);
        employeeDAO = database.employeeDAO();
        allEmployees = employeeDAO.getAllEmployees();
    }

    public void insert(Employee employee) {
        executorService.execute(() -> employeeDAO.insert(employee));
    }

    public void update(Employee employee) {
        executorService.execute(() -> employeeDAO.update(employee));
    }

    public void delete(Employee employee) {
        executorService.execute(() -> employeeDAO.delete(employee));
    }

    public void deleteAllEmployees() {
        executorService.execute(employeeDAO::deleteAllEmployees);
    }

    public LiveData<List<Employee>> getAllEmployees() {
        return allEmployees;
    }


}
