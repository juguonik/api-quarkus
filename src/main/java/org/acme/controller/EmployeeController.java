package org.acme.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.entity.Employee;
import org.acme.service.EmployeeService;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class EmployeeController {
	
	@Inject
	EmployeeService employeeService;
	
	@GET
	
	public List<Employee> list() {
		return employeeService.list();
	}
	
	@POST
	
	public Response create(Employee e) {
		employeeService.create(e);
		return Response.ok(e).status(201).build();
		
		
	}
	
	@PUT
	public Response update(Employee e){
		employeeService.update(e);
		return Response.ok(e).status(200).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id){
		employeeService.delete(id);
		return Response.status(204).build();

}
}

