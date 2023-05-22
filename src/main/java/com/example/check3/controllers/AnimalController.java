package com.example.check3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long idAnimal) {
		Optional<Animal> selecionado = animalRepository.findById(idAnimal);
		if(selecionado.isPresent()) {
			Animal animal = selecionado.get();
			model.addAttribute("animal", animal);
			return "animal/edit";
		} else {
			return "redirect:/animal";
		}
	}
	
	@PostMapping("/edit/{id}")
	public String create(@PathVariable("id") Long id, @ModelAttribute("animal") Animal objAnimal, Model model) {
		Animal animal = animalRepository.findById(id).orElse(null);
		
		animal.setNome(objAnimal.getNome());
		animal.setRaca(objAnimal.getRaca());
		animal.setEspecie(objAnimal.getEspecie());
		animal.setIdade(objAnimal.getIdade());
		
		animalRepository.save(animal);
		
		model.addAttribute("animal", animal);
		
		return "redirect:/";
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
