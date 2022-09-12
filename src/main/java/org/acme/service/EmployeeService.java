package org.acme.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.acme.entity.Employee;

@ApplicationScoped
public class EmployeeService {
	

	public List<Employee> getAll() {
		return Employee.listAll();
	}

//	Content-Type application/json(headers) + body json no Postman
	@Transactional
	public Employee create(Employee e) {
		Employee employee = new Employee();
		employee.firstName = e.getFirstName();
		employee.lastName = e.getLastName();
		employee.department = e.getDepartment();
		employee.role = e.getRole();
		employee.salary = e.getSalary();
		
		return employee;

	}
	
//	Content-Type application/json(headers) + body json no Postman (id via body)
	@Transactional
	public Employee update(Long id, Employee employeeUp ) {
		
		Employee employee = new Employee();
		
		Optional<Employee> employeeOp = Employee.findByIdOptional(id);

		if (employeeOp.isEmpty()) {
			throw new NullPointerException("Not found!");
		}

		employee = employeeOp.get();
		employee.setFirstName(employeeUp.getFirstName());
		employee.setLastName(employeeUp.getLastName());
		employee.setDepartment(employeeUp.getDepartment());
		employee.setRole(employeeUp.getRole());
		employee.setSalary(employeeUp.getSalary());
		
		employee.persist();
		return employee;
	}

//	Content-Type application/json(headers) + id via Url no Postamn
	@Transactional
	public void delete(Long id) {
		Employee.deleteById(id);
	}




}
	







	
	

