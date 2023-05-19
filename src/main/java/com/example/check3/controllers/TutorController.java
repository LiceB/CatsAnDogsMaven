package com.example.check3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.check3.model.Tutor;
import com.example.check3.model.repository.TutorRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tutor")
public class TutorController {

	@Autowired
	private TutorRepository tutorRepository;
	
	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("tutor/index");
		List<Tutor> listaTutor = tutorRepository.findAll();
		model.addObject("tutores", listaTutor);
		return model;
	}
	
	@GetMapping("create")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView("tutor/create");
		return model;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Tutor> create(@Valid @RequestBody Tutor tutor) {
		tutorRepository.save(tutor);
		return new ResponseEntity<Tutor>(tutor, HttpStatus.CREATED);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		tutorRepository.deleteById(id);
		return "redirect:/tutor";
	}
}
