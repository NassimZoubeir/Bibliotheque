package com.example.bibliothequecours.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bibliothequecours.entity.Auteur;
import com.example.bibliothequecours.entity.Livre;
import com.example.bibliothequecours.repository.AuteurRepository;
import com.example.bibliothequecours.repository.LivreRepository;

@Controller
public class AuteurController {
	
		 @Autowired
		 private AuteurRepository auteurRepository;
		 
		 @Autowired
		 private LivreRepository livreRepository;

		 @GetMapping("/auteurs/{id}")
		 public String getAuteurDetails(@PathVariable Long id, Model model) {
		 Auteur auteur = auteurRepository.findById(id).orElse(null);
		 List<Livre> livres = livreRepository.findByAuteur(auteur);
		 model.addAttribute("auteur", auteur);
		 model.addAttribute("livres", livres);
		 return "auteurDetail";
	 }

}
