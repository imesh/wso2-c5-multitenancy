package org.wso2.msf4j.example;

import org.wso2.msf4j.example.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance;
    private List<Employee> employees;

    private Database() {
        employees = new ArrayList<>();
        employees.add(new Employee("Employee 1", 21));
        employees.add(new Employee("Employee 2", 22));
        employees.add(new Employee("Employee 3", 23));
        employees.add(new Employee("Employee 4", 24));
        employees.add(new Employee("Employee 5", 25));
        employees.add(new Employee("Employee 6", 26));
        employees.add(new Employee("Employee 7", 27));
        employees.add(new Employee("Employee 8", 28));
        employees.add(new Employee("Employee 9", 29));
        employees.add(new Employee("Employee 10", 30));
    }

    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
