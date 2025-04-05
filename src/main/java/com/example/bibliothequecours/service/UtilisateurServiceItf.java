package com.example.bibliothequecours.service;

import java.util.List;

import com.example.bibliothequecours.entity.Emprunt;
import com.example.bibliothequecours.entity.Utilisateur;

public interface UtilisateurServiceItf {
	void creerUtilisateur(Utilisateur utilisateur);
	Utilisateur lireUtilisateurParLogin(String login);
	Utilisateur lireUtilisateurParId(Long id);
	void emprunterListLivreUtilisateur(List<Long> livreEmprunterListId, Long idUtilisateur);
	List<Emprunt> getEmpruntLivreList(Long idUtilisateur);
	Emprunt getEmpruntById(Long id);
	void majEmprunt(Emprunt emprunt);
	boolean verifierEmail(String token);

	
}
