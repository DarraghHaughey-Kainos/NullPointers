package org.example.client;

public class FailedToUpdateDeliveryEmployee extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to update delivery employee";
    }
}
