/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.employeepract;

/**
 *
 * @author Kucher PC
 */ 
public class EmployeePract {

    public static void main(String[] args) {
       try {
            Employee employee = new Employee("John", "Doe", 5000);
            System.out.println("Employee created: " + employee.getName() + " " + employee.getSurname() +
                    ", Salary: " + employee.getSalary());
            
        } catch (FieldLengthLimitException | IncorrectSalaryException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
