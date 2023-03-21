package org.guptaji.resource;

import org.guptaji.entity.Employee;
import org.guptaji.repository.EmployeeRepo;
import org.jboss.logging.annotations.Pos;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/employee")
public class EmployeeResource {

    @Inject
    public EmployeeRepo employeeRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployeeData(){
        return Response.ok(employeeRepo.listAll()).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response saveEmployeeData(Employee employee){
        employeeRepo.persist(employee);
        if (employeeRepo.isPersistent(employee)){
            return Response.ok("Done dana done dan").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("/employeeOfAnOrg/{orgName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByOrg(@PathParam("orgName") String orgName){
        return Response.ok(employeeRepo.list("orgName", orgName)).build();
    }

    @GET
    @Path("/employeeOfAnOrgAndAge/{orgName}/{age}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByOrgAndAge(@PathParam("orgName") String orgName, @PathParam("age") int age){
        // Here we used positional bind parameters i.e. at the param side first parameter should be orgName and
        // second should be age.

        // parameters in query string should match with entity
        return Response.ok(employeeRepo.list("orgName = ?1 and age >= ?2", orgName, age)).build();
    }

    // this API is not working right now later we need to see how to resolve that issue.
//    @GET
//    @Path("/employeeOfAnOrgAndAge/{orgName}/{salary}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getEmployeeByOrgAndSalary(@PathParam("orgName") String orgName, @PathParam("salary") int salary){
        // Here we used named bind parameters i.e. we will provide a map which will contain varianle name of query string
        // and it's corresponding values
//        Map<String, Object> params = new HashMap<>();
//        params.put("orgName", orgName);
//        params.put("salary", salary);
//        return Response.ok(employeeRepo.list("orgName = :orgName and salary >= :salary", params)).build();

//        return Response.ok(employeeRepo.list("orgName = ?1 and salary >= ?2", orgName, salary)).build();
//    }
}
