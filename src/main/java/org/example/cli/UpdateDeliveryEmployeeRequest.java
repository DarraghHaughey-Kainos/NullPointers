package org.example.cli;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * extends employee request class. can update a delivery employees first name, last name, salary and bank account number
 */
public class UpdateDeliveryEmployeeRequest extends UpdateEmployeeRequest{

    /**
     * constructor with args - creates an update delivery employee request object with a first name, last name, salary and bank account number
     * @param firstName
     * @param lastName
     * @param salary
     * @param bankAccountNumber
     */
    public UpdateDeliveryEmployeeRequest(@JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("salary") double salary, @JsonProperty("bankAccountNumber") String bankAccountNumber) {
        super(firstName, lastName, salary, bankAccountNumber);
    }
}
