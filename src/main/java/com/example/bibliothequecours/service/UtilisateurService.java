package com.example.bibliothequecours.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliothequecours.entity.Livre;
import com.example.bibliothequecours.entity.Utilisateur;
import com.example.bibliothequecours.repository.LivreRepository;
import com.example.bibliothequecours.repository.UtilisateurRepository;

@Service
public  class UtilisateurService implements UtilisateurServiceItf {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private  LivreRepository  livreRepository;

	@Autowired
	private  LivreServiceItf  livreService;

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);	
	}
	@Override
	public Utilisateur lireUtilisateurParLogin(String login) {
		return utilisateurRepository.findByLogin(login);
	}
	@Override
	public Utilisateur lireUtilisateurParId(Long id) {
		Utilisateur utilisateur = utilisateurRepository.findById(id).get();
		return utilisateur;
	}

	@Override
	public  List<Livre>  getEmpruntLivreList(Long  idUtilisateur)  {
		Utilisateur  utilisateur  =  lireUtilisateurParId(idUtilisateur);
		return  utilisateur.getEmprunterLivreList();
	}
	@Override
	public void emprunterListLivreUtilisateur(List<Long> livreIdList, Long idUtilisateur, LocalDate dateEmprunt, LocalDate dateRetour) {
	    Utilisateur utilisateur = lireUtilisateurParId(idUtilisateur);
	    List<Livre> livreList = livreService.getLivreEmprunterListParLivreIdList(livreIdList);

	    for (Livre livre : livreList) {
	        if (livre.getNbExemplaire() <= 0) {
	            throw new IllegalStateException("Le livre \"" + livre.getTitre() + "\" n'est pas disponible.");
	        }

	        // Ajouter le livre à la liste des emprunts de l'utilisateur
	        utilisateur.getEmprunterLivreList().add(livre);

	        // Définir les dates d'emprunt et de retour
	        livre.setDateEmprunt(dateEmprunt);
	        livre.setDateRetourPrevue(dateRetour);

	        // Mettre à jour le nombre d'exemplaires disponibles
	        livre.setNbExemplaire(livre.getNbExemplaire() - 1);

	        // Sauvegarder les modifications du livre
	        livreRepository.save(livre);
	    }

	    utilisateurRepository.save(utilisateur);
	}

	
}
