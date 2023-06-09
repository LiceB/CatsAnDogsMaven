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

import com.example.check3.model.Veterinario;
import com.example.check3.model.repository.VeterinarioRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/veterinario")
public class VeterinarioController {
	
	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	@GetMapping("")
	public ModelAndView get() {
		ModelAndView model = new ModelAndView("veterinario/index");
		
		List<Veterinario> listaVeterinario = veterinarioRepository.findAll();
		
		model.addObject("veterinarios", listaVeterinario);
		return model;
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id")Long idVeterinario) {
		Optional<Veterinario> selecionado = veterinarioRepository.findById(idVeterinario);
		if(selecionado.isPresent()) {
			Veterinario veterinario = selecionado.get();
			model.addAttribute("veterinario", veterinario);
			return "veterinario/edit";
		} else {
			return "retirect:/veterinario";
		}
	}
	
	@PostMapping("/edit/{id}")
	public String create(@PathVariable("id") Long id, @ModelAttribute("veterinario") Veterinario objVeterinario, Model model) {
		Veterinario veterinario = veterinarioRepository.findById(id).orElse(null);
		
		veterinario.setNome(objVeterinario.getNome());
		veterinario.setCRV(objVeterinario.getCRV());
		veterinario.setEspecialidade(objVeterinario.getEspecialidade());
		veterinario.setDisponibilidade(objVeterinario.getDisponibilidade());

		veterinarioRepository.save(veterinario);

		model.addAttribute("veterinario", veterinario);

		return "redirect:/";
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView("veterinario/create");
		return model;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Veterinario> create(@Valid @RequestBody Veterinario veterinario) {
		veterinarioRepository.save(veterinario);
		
		return new ResponseEntity<Veterinario>(veterinario, HttpStatus.CREATED);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		veterinarioRepository.deleteById(id);
		return "redirect:/veterinario";
	}
}
