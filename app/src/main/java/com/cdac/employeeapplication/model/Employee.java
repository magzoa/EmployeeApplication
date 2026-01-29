package com.cdac.employeeapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.math.BigDecimal;
@Entity(tableName = "employees")
public class Employee {

    @PrimaryKey(autoGenerate = true)
private Integer id;
private String name;
private String role;
private double salary;

    public Employee(String name, String role, double salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
