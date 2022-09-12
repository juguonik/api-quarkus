package org.acme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.acme.controller.EmployeeController;
import org.acme.entity.Employee;
import org.acme.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class EmployeeControllerTestsMocking {

	@InjectMock
	EmployeeService employeeService;

	@Inject
	EmployeeController employeeController;

	public Employee employee;

	@BeforeEach
	void setUp() {
		employee = new Employee();
		employee.setFirstName("FirstNameTest");
		employee.setLastName("LastNameTest");
		employee.setDepartment("DepartmentTest");
		employee.setRole("RoleTest");
		employee.setSalary(1.00);
		employee.setId(1L);

	}

	@Test
	void getAll() {
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(employee);
		Mockito.when(employeeService.getAll()).thenReturn(employees);
		Response response = employeeController.getAll();
		assertNotNull(response);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertNotNull(response.getEntity());
		@SuppressWarnings("unchecked")
		List<Employee> entity = (List<Employee>) response.getEntity();
		assertFalse(entity.isEmpty());
		assertEquals("FirstNameTest", entity.get(0).getFirstName());
		assertEquals("LastNameTest", entity.get(0).getLastName());
		assertEquals("DepartmentTest", entity.get(0).getDepartment());
		assertEquals("RoleTest", entity.get(0).getRole());
		assertEquals(1.00, entity.get(0).getSalary());
		assertEquals(1L, entity.get(0).getId());

	}

	@Test
	void create() {

		Employee newEmployee = new Employee();
		newEmployee.setFirstName("FirstNameSecondTest");
		newEmployee.setLastName("LastNameSecondTest");
		newEmployee.setDepartment("DepartmentSecondTest");
		newEmployee.setRole("RoleSecondTest");
		newEmployee.setSalary(100.00);

		Response response = employeeController.create(newEmployee);
		assertNotNull(response);
		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
		assertNotNull(response.getEntity());

	}


	@Test
	void update() {

		Employee updatedEmployee = new Employee();
		updatedEmployee.setDepartment("NewDepartment");

	    Response response = employeeController.update(1L, updatedEmployee);


		assertNotNull(response);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertNotNull(response.getEntity());
		Employee entity = (Employee) response.getEntity();
		assertEquals(1L, entity.getId());
		assertEquals("FirstNameTest", entity.getFirstName());
		assertEquals("LastNameTest", entity.getLastName());
		assertEquals("NewDepartment", entity.getDepartment());
		assertEquals("RoleTest", entity.getRole());
		assertEquals(1.00, entity.getSalary());
	}
	
	
	@Test
	void delete() {
		
		
	    Response response = employeeController.delete(1L);
	    assertNotNull(response);
	    assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
	    assertNull(response.getEntity());
	}
}
