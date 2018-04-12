package com.pmc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pmc.dao.EmployeeDao;
import com.pmc.model.Employee;

@Controller
public class EmployeeController {

	@Autowired
	@Qualifier("employeeDao")
	private EmployeeDao dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("addEmployee", "employee", new Employee());
    }

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	@Transactional
	public String saveEmployee() {

		Employee emp = new Employee();
		emp.setName("test");
		// emp.setId(null);
		dao.saveOrUpdate(emp);
		return "hello";
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String submit(@ModelAttribute("employee")Employee employee, BindingResult result, ModelMap model)
	{
	model.addAttribute("name", employee.getName());
    model.addAttribute("id", employee.getId());
	return "viewEmployee";
	}

}