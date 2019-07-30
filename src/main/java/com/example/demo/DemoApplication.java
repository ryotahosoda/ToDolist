package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.ArrayUtils;


import java.util.List;

@SpringBootApplication
//@Controller
public class DemoApplication {

//	@Autowired
//	TodoRepository todoRepository;
//	@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
//	public String index(Model model, Model model2){
//		List todolist=(List) todoRepository.findAll();
//		model.addAttribute("todolist", todolist);
//        model2.addAttribute("todo", new Todo());
//		return "index";
//	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}