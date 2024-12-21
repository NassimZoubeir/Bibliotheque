package com.example.bibliothequecours.service;

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
	public  void  emprunterListLivreUtilisateur(List<Long>  livreIdList,  Long  idUtilisateur)  {
		Utilisateur  utilisateur  =  lireUtilisateurParId(idUtilisateur);
		List<Livre>  livreList  =  livreService.getLivreEmprunterListParLivreIdList(livreIdList);
		utilisateur.getEmprunterLivreList().addAll(livreList);
		System.out.println("majLivreEmprunterListUtilisateur  utilisateur="  +  utilisateur);
		utilisateurRepository.save(utilisateur);
		for(int  i=0;  i  <  livreList.size();  i++)  {
			livreRepository.decrementerNbExemplaireLivre(livreList.get(i).getId());
		}
	}
	@Override
	public  List<Livre>  getEmpruntLivreList(Long  idUtilisateur)  {
		Utilisateur  utilisateur  =  lireUtilisateurParId(idUtilisateur);
		return  utilisateur.getEmprunterLivreList();
	}
	
}
