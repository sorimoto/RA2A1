package com.ra1.Controllers;

import com.ra1.model.Customer;
import com.ra1. Repository.CustomerRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/jdbctemplate") 
public class RalController{
	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping("/hello")
	public String jdbctemp() {
		return "hello";
	}
	@PostMapping("/init-db")
	public String initializeDatabase() {
		customerRepository.createTableCustomers();
		customerRepository.insertSampleData();
		return "Base de dades inicialitzada correctament";
	}
	
	@GetMapping("/findAllCustomers")
	public List<Customer> getAllCustomers(){
		return customer Repository.findAll();
	}
}