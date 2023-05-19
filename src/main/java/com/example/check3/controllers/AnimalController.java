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

import com.example.check3.model.Animal;
import com.example.check3.model.repository.AnimalRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/animal")
public class AnimalController {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("animal/index");
		List<Animal> listaAnimal = animalRepository.findAll();
		model.addObject("animais", listaAnimal);
		return model;
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView("animal/create");
		return model;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Animal> create(@Valid @RequestBody Animal animal) {
		animalRepository.save(animal);
		
		return new ResponseEntity<Animal>(animal, HttpStatus.CREATED);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		animalRepository.deleteById(id);
		return "redirect:/animal";
	}
}
