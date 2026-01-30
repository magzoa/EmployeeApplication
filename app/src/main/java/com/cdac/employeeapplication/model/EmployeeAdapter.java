package com.cdac.employeeapplication.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cdac.employeeapplication.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employeeList = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEdit(Employee employee);
        void onDelete(Employee employee);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setEmployees(List<Employee> employees) {
        this.employeeList = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.tvName.setText(employee.getName());
        holder.tvRole.setText(employee.getRole());
        holder.tvSalary.setText(String.valueOf(employee.getSalary()));

        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) listener.onEdit(employee);
        });

        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) listener.onDelete(employee);
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvRole, tvSalary;
        Button btnEdit, btnDelete;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvRole = itemView.findViewById(R.id.tvRole);
            tvSalary = itemView.findViewById(R.id.tvSalary);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}

