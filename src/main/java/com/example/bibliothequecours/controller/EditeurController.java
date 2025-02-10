package com.example.bibliothequecours.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bibliothequecours.entity.Editeur;
import com.example.bibliothequecours.entity.Livre;
import com.example.bibliothequecours.repository.EditeurRepository;
import com.example.bibliothequecours.repository.LivreRepository;


@Controller
public class EditeurController {
	
	 @Autowired
	 private EditeurRepository editeurRepository;
	 
	 @Autowired
	 private LivreRepository livreRepository;

	    @GetMapping("/editeurs/{id}")
	    public String getEditeurDetails(@PathVariable Long id, Model model) {
	        Editeur editeur = editeurRepository.findById(id).orElse(null);
	        List<Livre> livres = livreRepository.findByEditeur(editeur);
	        model.addAttribute("editeur", editeur);
	        model.addAttribute("livres", livres);
	        return "editeurDetail";
	    }

}
