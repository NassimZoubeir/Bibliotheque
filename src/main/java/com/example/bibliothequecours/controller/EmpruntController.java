package com.example.bibliothequecours.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bibliothequecours.entity.Emprunt;
import com.example.bibliothequecours.service.LivreServiceItf;
import com.example.bibliothequecours.service.UtilisateurServiceItf;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EmpruntController {
	
	@Autowired
	private UtilisateurServiceItf utilisateurService;
	
	@Autowired
	private  LivreServiceItf  livreService;
	
	@RequestMapping("/valider-panier")
	public String validerPanier(Model model, HttpServletRequest request) {
		System.out.println("==== /valider-panier ====");
		List<Long> livreEmprunterListId = (List<Long>) request.getSession().getAttribute("livreEmprunterListId");
		System.out.println("livreEmprunterListId=" + livreEmprunterListId);
		if(livreEmprunterListId != null) {
			Long idUtilisateur = (Long) request.getSession().getAttribute("id");
	    	utilisateurService.emprunterListLivreUtilisateur(livreEmprunterListId, idUtilisateur);
	    	request.getSession().removeAttribute("livreEmprunterListId");
		}
		else System.out.println("Pas de livre emprunté");
		return "redirect:/afficher-emprunt";
	}
	@RequestMapping("/afficher-emprunt")
    public String afficherEmprunt(Model model, HttpServletRequest request) {
		System.out.println("==== /afficher-emprunt ====");
		Long idUtilisateur = (Long) request.getSession().getAttribute("id");
		List<Emprunt> empruntList = utilisateurService.getEmpruntLivreList(idUtilisateur);
		System.out.println("empruntList=" + empruntList);
		model.addAttribute("titre", "Emprunt de livre");
		model.addAttribute("empruntList", empruntList);
		return "emprunt";
	}
	@RequestMapping("/retourner-emprunt/{id}")
    public String retournerEmprunt(@PathVariable Long id, Model model, HttpServletRequest request) {
		System.out.println("==== /retourner-emprunt ====");
		System.out.println("id=" + id);
		Emprunt emprunt = utilisateurService.getEmpruntById(id);
		System.out.println("emprunt:" + emprunt);
		emprunt.setRetourDate(new Date());
		utilisateurService.majEmprunt(emprunt);
		livreService.incrementerNbExemplaireLivre(id);
		return "redirect:/afficher-emprunt";
	}
}
