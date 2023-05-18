package com.example.check3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.check3.model.Veterinario;
import com.example.check3.model.repository.VeterinarioRepository;

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
	public String getById(Model model, @PathVariable("id")Integer idCategoria) {
		model.addAttribute("idCategoria", idCategoria);
		return "veterinario/edit";
	}
	
	@GetMapping("/create")
	public ModelAndView create() {
		ModelAndView model = new ModelAndView("veterinario/create");
		return model;
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("veterinario")Veterinario objVeterinario) {
		veterinarioRepository.save(objVeterinario);
		
		return "redirect:/veterinario";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		veterinarioRepository.deleteById(id);
		return "redirect:/veterinario";
	}
}
