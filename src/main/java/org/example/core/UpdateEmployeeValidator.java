package org.example.core;

import org.example.cli.UpdateEmployeeRequest;

public class UpdateEmployeeValidator {

    /**
     * checks if data given to update an employee is valid. if a check fails, an appropriate error message is returned.
     * Otherwise, null is returned
     * @param employeeToUpdate employee to be updated
     * @return String appropriate error message, or null if checks pass
     */
    public String isValidEmployeeUpdate(UpdateEmployeeRequest employeeToUpdate) {

        if (employeeToUpdate.getSalary() < 0) {
            return "Salary cannot be less than zero";
        }

        if (employeeToUpdate.getBankAccountNumber().length() != 8) {
            return "Bank account number length must be 8 provided ";
        }

        // if checks pass, return null
        return null;
    }
}
