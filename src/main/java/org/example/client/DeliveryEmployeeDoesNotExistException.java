package org.example.client;

public class DeliveryEmployeeDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Delivery Employee does not exist";
    }
}
