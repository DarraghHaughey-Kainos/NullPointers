package org.example.client;

public class FailedToDeleteDeliveryEmployeeException extends Throwable {
     @Override
        public String getMessage(){

         return "Failed to delete delivery employee does not exist";
        }
    }

