package org.example.resources;

import org.example.api.DeliveryEmployeeService;
import org.example.cli.DeliveryEmployee;
import org.example.cli.DeliveryEmployeeRequest;
import org.example.client.DoesNotExistException;
import org.example.client.FailedToCreateException;
import org.example.client.FailedToGetException;
import org.example.client.ValidationFailedException;
import io.swagger.annotations.Api;
import org.example.db.DeliveryEmployeeDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api("NullPointers api")
@Path("/api")
public class DeliveryEmployeeController {

    // Instantiates DeliveryEmployee service, passes through DeliveryEmployee DAO
    DeliveryEmployeeService deliveryEmployeeService = new DeliveryEmployeeService(new DeliveryEmployeeDAO());

    // GET route to capture all employees in DB
    @GET
    @Path("/employee/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees(){
        DeliveryEmployeeDAO employeeDAO = new DeliveryEmployeeDAO();
        return Response.ok().entity(employeeDAO.getDeliveryEmployees()).build();
    }

    // POST route to create a new delivery employee in DB
    @POST
    @Path("/employee/delivery")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDeliveryEmployee(DeliveryEmployeeRequest deliveryEmployeeRequest) {
        try {
            return Response.ok(deliveryEmployeeService.createDeliveryEmployee(deliveryEmployeeRequest)).build();
        } catch (FailedToCreateException e) {
            System.err.println(e.getMessage());
            return Response.serverError().entity(e.getMessage()).build();
        } catch (ValidationFailedException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * gets first name, last name, salary, bank account number of a delivery employee based on the employee id
     * @param id employee id of employee
     * @return id of employee
     */
    @GET
    @Path("/employee/delivery/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeliveryEmployeeById(@PathParam("id") int id){
        try {
            // response 200 ok, return delivery employee id
            return Response.ok(deliveryEmployeeService.getDeliveryEmployeeById(id)).build();
        } catch (DoesNotExistException e) {
            System.err.println(e.getMessage());
            // response 400 bad request, delivery employee requested doesn't exist
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (FailedToGetException e) {
            System.err.println(e.getMessage());
            // response 500 internal server error, sql query failure
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
