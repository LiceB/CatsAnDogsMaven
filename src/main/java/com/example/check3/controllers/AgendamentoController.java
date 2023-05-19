package com.example.check3.controllers;

import java.util.List;
import java.util.Optional;

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
	
	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("agendamento/index");
		List<Agendamento> listaAgendamento = agendamentoRepository.findAll();
		
		model.addObject("agendamentos", listaAgendamento);
		return model;
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView("agendamento/create");		
		return model;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Agendamento> create(@Valid @RequestBody Agendamento agendamento) {
		agendamentoRepository.save(agendamento);
		
		return new ResponseEntity<Agendamento>(agendamento, HttpStatus.CREATED);
	}
	
//	@PostMapping("/edit/{id}")
//	public ResponseEntity<Agendamento> edit(@PathVariable("id") Long id, @Valid @RequestBody Agendamento agendamento) {
//	    Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(id);
//	    if (optionalAgendamento.isPresent()) {
//	        Agendamento existingAgendamento = optionalAgendamento.get();
//	        existingAgendamento.setIdAnimal(agendamento.getIdAnimal());
//	        existingAgendamento.setIdTutor(agendamento.getIdTutor());
//	        existingAgendamento.setIdVeterinario(agendamento.getIdVeterinario());
//	        existingAgendamento.setEspecialidade(agendamento.getEspecialidade());
//	        
//	        agendamentoRepository.save(existingAgendamento);
//	        return new ResponseEntity<>(existingAgendamento, HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		agendamentoRepository.deleteById(id);
		return "redirect:/agendamento";
	}
}
