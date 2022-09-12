package org.acme;

import static io.restassured.RestAssured.given;


import java.util.HashMap;

import java.util.Map;

import org.acme.controller.EmployeeController;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
@TestHTTPEndpoint(EmployeeController.class)
public class EmployeeControllerTest {
	
	
    @Test
    public void testListEmployees() {   	
    	
        given()
          .when().get()
          .then()
             .statusCode(200)
             .body(is("[{\"department\":\"HR\",\"firstName\":\"Morgana\",\"id\":1,\"lastName\":\"Stevenson\",\"role\":\"HR Coordinator\",\"salary\":6000.0},{\"department\":\"IT\",\"firstName\":\"João\",\"id\":2,\"lastName\":\"Souza\",\"role\":\"IT Intern\",\"salary\":1000.0},{\"department\":\"Logistics\",\"firstName\":\"Ana\",\"id\":3,\"lastName\":\"Paz\",\"role\":\"Logistics Analyst\",\"salary\":3000.0}]"));
    }
    
    @Test
    public void testCreateEmployee() {
    	    	
    	Map<String,String> employee = new HashMap<>();
		employee.put("firstName", "Julia");
		employee.put("lastName", "Sampaio");
		employee.put("department", "Compliance");
		employee.put("role", "Assistant");
		employee.put("salary", "2500.00");
    	given()
        .contentType ("application/json")
        .body(employee)
        .when()
        .post()
        .then()
        .statusCode(201);
    }
    
    
    @Test
    public void testUpdateEmployee() {
    	
    }

}