package com.example.check3.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.check3.model.Agendamento;
import com.example.check3.model.Animal;
import com.example.check3.model.Tutor;
import com.example.check3.model.Veterinario;
import com.example.check3.model.repository.AgendamentoRepository;
import com.example.check3.model.repository.AnimalRepository;
import com.example.check3.model.repository.TutorRepository;
import com.example.check3.model.repository.VeterinarioRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/agendamento")
public class AgendamentoController {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private TutorRepository tutorRepository;
	
	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	
	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("agendamento/index");
		List<Agendamento> listaAgendamento = agendamentoRepository.findAll();
		
		model.addObject("agendamentos", listaAgendamento);
		return model;
	}
	
	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Long idAgendamento) {
		Optional<Agendamento> selecionado = agendamentoRepository.findById(idAgendamento);
		if(selecionado.isPresent()) {
			Agendamento agendamento = selecionado.get();
			model.addAttribute("agendamento", agendamento);
			return "agendamento/edit";
		} else {
			return "redirect:/agendamento";
		}
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView("agendamento/create");
		List<Animal> animal = animalRepository.findAll();
		List<Tutor> tutor = tutorRepository.findAll();
		List<Veterinario> veterinario = veterinarioRepository.findAll();
		
		model.addObject("animal", animal);
		model.addObject("tutor", tutor);
		model.addObject("veterinario", veterinario);
		return model;
	}
	
	@PostMapping("/create-form")
	public ResponseEntity<Agendamento> createFrom(@Valid @RequestBody Agendamento objAgendamento) {
		agendamentoRepository.save(objAgendamento);
		return new ResponseEntity<Agendamento>(objAgendamento, HttpStatus.CREATED);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Agendamento> create(@Valid @RequestBody Agendamento agendamento) {
		agendamentoRepository.save(agendamento);
		
		return new ResponseEntity<Agendamento>(agendamento, HttpStatus.CREATED);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		agendamentoRepository.deleteById(id);
		return "redirect:/agendamento";
	}
}
