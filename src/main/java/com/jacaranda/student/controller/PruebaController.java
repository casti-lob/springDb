package com.jacaranda.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.student.modell.Student;
import com.jacaranda.student.service.StudentService;

@Controller
public class PruebaController {
	
	@Autowired
	private StudentService repositorio;
	
	@GetMapping("list")
	public String listStudent(Model model) {
		model.addAttribute("list", repositorio.getStudents());
		return "listStudent";
	}
	
//	@GetMapping("login")
//	public String loginUser(Model model) {
//		
//		return "login";
//	}
	
	@GetMapping("add")
	public String addStudent(Model model) {
		Student student = new Student();
		model.addAttribute("newStudent", student);
		return "addStudent";
	}
	
	@PostMapping("add/submit")
	public String addSubmit(@Validated @ModelAttribute("newStudent") Student student,BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			return "addStudent";
		else
			repositorio.add(student);
			return "redirect:/list";
		
	}
	
	@GetMapping("delStudent")
	public String deleteStudent(Model model, 
			@RequestParam(name="id")Long id)
	 {
		
		Student student = repositorio.getStudent(id);
		model.addAttribute("student", student);
		return "deleteStudent";
	}
	
	@PostMapping("delStudent/submit")
	public String deleteSubmit(@ModelAttribute("student") Student student) {
		repositorio.delete(student);
		return "redirect:/list";
	}
	
	@GetMapping("editStudent")
	public String editStudent(Model model, @RequestParam(name="id")Long id)
	{
		Student student = repositorio.getStudent(id);
		model.addAttribute("student", student);
		return "editStudent";
	}
	
	@PostMapping("editStudent/submit")
	public String editSubmit(@ModelAttribute("student") Student student) {
		repositorio.update(student);
		return "redirect:/list";
	}
}
