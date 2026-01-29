package com.cdac.employeeapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.cdac.employeeapplication.model.Employee;
import com.cdac.employeeapplication.model.EmployeeViewModel;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private EmployeeViewModel employeeViewModel;
    private EditText nameInput, roleInput,salaryInput;
    Button add;
    TextView employeedetails;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nameInput=findViewById(R.id.nameInput);
        roleInput=findViewById(R.id.roleInput);
        salaryInput=findViewById(R.id.salaryInput);
        employeeViewModel=new ViewModelProvider(this).get(EmployeeViewModel.class);
        add=findViewById(R.id.addButton);
        employeedetails=findViewById(R.id.employeedetails);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString().trim();
                String role=roleInput.getText().toString().trim();


              String  text = salaryInput.getText().toString().trim().replace(",", ".");
                double salary = Double.parseDouble(text);



                if (!name.isEmpty()) {
                    employeeViewModel.insert(new Employee(name, role,salary));
                    Toast.makeText(MainActivity.this, "Employee added!", Toast.LENGTH_SHORT).show();
                }          }
        });
        employeeViewModel.getAllEmployees().observe(this, employees->{
                    String data=employeedetails.getText().toString();
                    StringBuffer sb=new StringBuffer(data);
            employeedetails.setText("");
                    for (Employee employee : employees) {
                        sb.append("Employee: " + employee.getName() + ", Age: " + employee.getRole()+ ", Salary: " + employee.getSalary()+"\n");
                    }
                    employeedetails.setText(sb);
                }
        );






    }
}