package com.example.bibliothequecours.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bibliothequecours.entity.Auteur;
import com.example.bibliothequecours.entity.Editeur;
import com.example.bibliothequecours.repository.AuteurRepository;
import com.example.bibliothequecours.repository.EditeurRepository;
import com.example.bibliothequecours.repository.LivreRepository;
import com.example.bibliothequecours.repository.UtilisateurRepository;

@Controller
public class AdminController {
	
	 @Autowired
	    private UtilisateurRepository utilisateurRepository;

	    @Autowired
	    private LivreRepository livreRepository;

	    @Autowired
	    private AuteurRepository auteurRepository;

	    @Autowired
	    private EditeurRepository editeurRepository;

	    @GetMapping("/admin")
	    public String adminTableau(Model model) {
	        model.addAttribute("utilisateurs", utilisateurRepository.findAll());
	        model.addAttribute("livres", livreRepository.findAll());
	        model.addAttribute("auteurs", auteurRepository.findAll());
	        model.addAttribute("editeurs", editeurRepository.findAll());
	        return "admin";
	    }

	    @PostMapping("/admin/supprimerUtilisateur/{id}")
	    public String supprimerUtilisateur(@PathVariable Long id) {
	        utilisateurRepository.deleteById(id);
	        return "redirect:/admin";
	    }

	    @PostMapping("/admin/supprimerLivre/{id}")
	    public String supprimerLivre(@PathVariable Long id) {
	        livreRepository.deleteById(id);
	        return "redirect:/admin";
	    }

	    @PostMapping("/admin/supprimerAuteur/{id}")
	    public String supprimerAuteur(@PathVariable Long id) {
	        auteurRepository.deleteById(id);
	        return "redirect:/admin";
	    }

	    @PostMapping("/admin/supprimerEditeur/{id}")
	    public String supprimerEditeur(@PathVariable Long id) {
	        editeurRepository.deleteById(id);
	        return "redirect:/admin";
	    }
	    
	    @PostMapping("/admin/creerAuteur")
	    public String creerAuteur(@RequestParam String nom, @RequestParam String bibliographie) {
	        Auteur auteur = new Auteur(nom, bibliographie);
	        auteurRepository.save(auteur);
	        return "redirect:/admin";
	    }

	    @PostMapping("/admin/creerEditeur")
	    public String creerEditeur(@RequestParam String nom, @RequestParam String lienDescription) {
	        Editeur editeur = new Editeur(nom, lienDescription);
	        editeurRepository.save(editeur);
	        return "redirect:/admin";
	    }
	 
}
