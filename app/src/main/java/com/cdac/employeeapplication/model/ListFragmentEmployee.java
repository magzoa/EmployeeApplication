package com.cdac.employeeapplication.model;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cdac.employeeapplication.R;


public class ListFragmentEmployee extends Fragment {

    private EmployeeViewModel employeeViewModel;
    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;

    public ListFragmentEmployee() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                                //este es el nombre del archivo
        View view = inflater.inflate(R.layout.fragment_list_employee, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewEmployees);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new EmployeeAdapter();
        recyclerView.setAdapter(adapter);

        employeeViewModel = new ViewModelProvider(requireActivity())
                .get(EmployeeViewModel.class);

        employeeViewModel.getAllEmployees().observe(getViewLifecycleOwner(), employees -> {
            adapter.setEmployees(employees);
        });

        adapter.setOnItemClickListener(new EmployeeAdapter.OnItemClickListener() {
            @Override
            public void onEdit(Employee employee) {
                showEditDialog(employee);
            }

            @Override
            public void onDelete(Employee employee) {
                employeeViewModel.delete(employee);
                Toast.makeText(getContext(), "Employee deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void showEditDialog(Employee employee) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Edit Employee");

        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);

        EditText nameInput = new EditText(getContext());
        nameInput.setText(employee.getName());
        layout.addView(nameInput);

        EditText roleInput = new EditText(getContext());
        roleInput.setText(employee.getRole());
        layout.addView(roleInput);

        EditText salaryInput = new EditText(getContext());
        salaryInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        salaryInput.setText(String.valueOf(employee.getSalary()));
        layout.addView(salaryInput);

        builder.setView(layout);

        builder.setPositiveButton("Save", (dialog, which) -> {
            employee.setName(nameInput.getText().toString());
            employee.setRole(roleInput.getText().toString());
            employee.setSalary(Double.parseDouble(salaryInput.getText().toString()));
            employeeViewModel.update(employee);
            Toast.makeText(getContext(), "Employee updated", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        builder.show();
    }
}
