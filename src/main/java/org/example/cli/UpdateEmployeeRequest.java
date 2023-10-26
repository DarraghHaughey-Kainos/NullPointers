package org.example.cli;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * object used to update an employee - excludes employees bank account number which cannot be updates
 * can update first name, last name, salary and bank account number
 */
public class UpdateEmployeeRequest {

    // attributes
    private String firstName;
    private String lastName;
    private double Salary;
    private String bankAccountNumber;

    /**
     * constructor with args - creates an update employee request object with a first name, last name, salary and bank account number
     * @param firstName
     * @param lastName
     * @param salary
     * @param bankAccountNumber
     */
    public UpdateEmployeeRequest(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("salary") double salary, @JsonProperty("bankAccountNumber") String bankAccountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        Salary = salary;
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }
}
