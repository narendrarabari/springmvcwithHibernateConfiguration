package com.pmc.dao;

import org.springframework.stereotype.Repository;

import com.pmc.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Employee> implements EmployeeDao {

	
	
	public EmployeeDaoImpl() {
		super();

		setClass(Employee.class);
	}	
}
