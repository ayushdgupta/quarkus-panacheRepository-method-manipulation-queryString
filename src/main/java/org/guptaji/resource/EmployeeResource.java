package org.guptaji.resource;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
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

    /**
     * Here we are doing sorting of the data for which orgName is 'optum' and doing sorting on behalf of
     * age and name.
     * @return
     */
    @GET
    @Path("/sortedData")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployeeDataSorted(){
        return Response.ok(employeeRepo.list("orgName", Sort.descending("age", "name"), "optum")).build();
        
        /*
        Output of above API, so sorting is done based on like first code will sort on the basis of age then if
        age of two outputs will match then code will go for name.
        [
  {
    "id": 1,
    "name": "Ayush",
    "salary": 82000,
    "orgName": "Optum",
    "age": 30
  },
  {
    "id": 5,
    "name": "Vinay",
    "salary": 100000,
    "orgName": "Optum",
    "age": 25
  },
  {
    "id": 6,
    "name": "Akash",
    "salary": 89000,
    "orgName": "optum",
    "age": 25
  }
]
         */
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
    @GET
    @Path("/employeeOfAnOrgAndSalary/{orgName}/{salary}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeByOrgAndSalary(@PathParam("orgName") String orgName, @PathParam("salary") int salary){
        // Here we used named bind parameters i.e. we will provide a map which will contain varianle name of query string
        // and it's corresponding values
        // for named bind params there are 3 methods
        //M1->
//        Map<String, Object> params = new HashMap<>();
//        params.put("orgName", orgName);
//        params.put("salary", salary);
//        return Response.ok(employeeRepo.list("orgName = :orgName and salary >= :salary", params)).build();

//        return Response.ok(employeeRepo.list("orgName = ?1 and salary >= ?2", orgName, salary)).build();

        // use it as-is
        // M2->
//        return Response.ok(employeeRepo.list("orgName = :orgName and salary >= :salary",
//                Parameters.with("orgName", orgName).and("salary", salary))).build();

        // generate a Map directly without creating it explicitely
        // M3->
        return Response.ok(employeeRepo.list("orgName = :orgName and salary >= :salary",
                Parameters.with("orgName", orgName).and("salary", salary).map())).build();
//        Person.find("name = :name and status = :status",
//                Parameters.with("name", "stef").and("status", Status.Alive));
    }

    @PUT
    @Path("/{salary}/{age}")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateEmployeeAge(@PathParam("salary") int salary, @PathParam("age") int age){
        // ?1, ?2 act as a place holder
        int update = employeeRepo.update("age = ?1 where salary = ?2", age, salary);
        if (update > 0){
            return Response.ok("Updated "+update+" data").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
