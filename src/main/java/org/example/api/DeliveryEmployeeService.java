package org.example.api;

import org.example.cli.DeliveryEmployee;
import org.example.cli.UpdateDeliveryEmployeeRequest;
import org.example.client.*;
import org.example.core.EmployeeValidator;
import org.example.core.UpdateDeliveryEmployeeValidator;
import org.example.cli.DeliveryEmployeeRequest;
import org.example.client.DoesNotExistException;
import org.example.client.FailedToCreateException;
import org.example.client.FailedToGetException;
import org.example.client.ValidationFailedException;
import org.example.core.EmployeeValidator;
import org.example.core.EmployeeRequestValidator;
import org.example.db.DeliveryEmployeeDAO;

import java.sql.SQLException;

public class DeliveryEmployeeService {
    private DeliveryEmployeeDAO dao;
    private EmployeeValidator employeeValidator = new EmployeeValidator();
    EmployeeRequestValidator employeeRequestValidator = new EmployeeRequestValidator();

    // create instance of update delivery employee validator class
    UpdateDeliveryEmployeeValidator updateDeliveryEmployeeValidator = new UpdateDeliveryEmployeeValidator();


    public DeliveryEmployeeService(DeliveryEmployeeDAO dao) {
        this.dao = dao;
    }

    /**
     * Attempts to create a delivery employee using the service data access object
     *
     * @param deliveryEmployeeRequest
     * @return the created delivery employee id
     * @throws FailedToCreateException
     */
    public int createDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployeeRequest) throws FailedToCreateException, ValidationFailedException {
        String error = employeeRequestValidator.isValidEmployee(deliveryEmployeeRequest);
        if (error != null) {
            throw new ValidationFailedException(error);
        }
        return dao.createDeliveryEmployee(deliveryEmployeeRequest);
    }

    /**
     * returns specific delivery employee based on employee id
     *
     * @param id employee id
     * @return delivery employee
     * @throws DoesNotExistException if no employee returned from database
     * @throws FailedToGetException  if sql statement failed
     */
    public DeliveryEmployee getDeliveryEmployeeById(int id) throws FailedToGetException, DeliveryEmployeeDoesNotExistException {

        // call getDeliveryEmployeeById method from DeliveryEmployeeDao class
        DeliveryEmployee deliveryEmployee = dao.getDeliveryEmployeeById(id);

        // if a delivery employee not returned, throw delivery employee does not exist exception
        if (deliveryEmployee == null) {
            throw new DeliveryEmployeeDoesNotExistException();
        }

        return deliveryEmployee;
    }

    /**
     * calls on dao to update delivery employee. first checks that all entered data is valid by calling the validator,
     * then calls to the dao to check a delivery employee with that id exists
     *
     * @param id                     employee id
     * @param deliveryEmployeeUpdate data used to update delivery employee
     * @throws FailedToUpdateDeliveryEmployee        if sql error
     * @throws FailedToGetException                  if sql error
     * @throws DeliveryEmployeeDoesNotExistException if delivery employee with given id doesn't exist
     * @throws InvalidUpdateRequestException         if validation checks fail
     */
    public void updateDeliveryEmployee(int id, UpdateDeliveryEmployeeRequest deliveryEmployeeUpdate) throws FailedToUpdateDeliveryEmployee, FailedToGetException, DeliveryEmployeeDoesNotExistException, InvalidUpdateRequestException {

        // call update delivery employee validator to check employee is a delivery employee
        String validateDeliveryEmployee = updateDeliveryEmployeeValidator.isValidEmployeeUpdate(deliveryEmployeeUpdate);

        // if employee exists, check data is valid using employee validator
        if (validateDeliveryEmployee != null) {
            throw new InvalidUpdateRequestException(validateDeliveryEmployee);
        }

        // call to dao to check employee with given id is a delivery employee
        if (dao.getDeliveryEmployeeById(id) == null) {
            throw new DeliveryEmployeeDoesNotExistException();
        }

        // call on dao to update delivery employee
        dao.updateDeliveryEmployee(id, deliveryEmployeeUpdate);
    }

    public void deleteDeliveryEmployee(int id) throws DeliveryEmployeeDoesNotExistException, FailedToGetException {
        try {
            DeliveryEmployee employeeToDelete = dao.getDeliveryEmployeeById(id);

            if (employeeToDelete == null) {
                throw new DeliveryEmployeeDoesNotExistException();
            }

            dao.deleteDeliveryEmployee(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
