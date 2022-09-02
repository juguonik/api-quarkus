package org.acme.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import javax.transaction.Transactional;

import org.acme.entity.Employee;

@ApplicationScoped
public class EmployeeService {
	

	
	public List<Employee> list() {
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
	public void update(Employee e) {
		Employee employee = Employee.findById(e.getId());
		employee.firstName = e.getFirstName();
		employee.lastName = e.getLastName();
		employee.department = e.getDepartment();
		employee.role = e.getRole();
		employee.salary = e.getSalary();

}
//	Content-Type application/json(headers) + id via Url no Postamn
	@Transactional
	public void delete(Long id) {
		Employee.deleteById(id);
	}

}
	







	
	

