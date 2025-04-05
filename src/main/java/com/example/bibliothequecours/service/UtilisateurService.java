package com.example.bibliothequecours.service;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bibliothequecours.entity.Emprunt;
import com.example.bibliothequecours.entity.Livre;
import com.example.bibliothequecours.entity.Utilisateur;
import com.example.bibliothequecours.entity.VerificationToken;
import com.example.bibliothequecours.repository.EmpruntRepository;
import com.example.bibliothequecours.repository.LivreRepository;
import com.example.bibliothequecours.repository.UtilisateurRepository;
import com.example.bibliothequecours.repository.VerificationTokenRepository;

@Service
public  class UtilisateurService implements UtilisateurServiceItf {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailServiceImpl emailService;

	@Autowired
	private  LivreRepository  livreRepository;

	@Autowired
	private  LivreServiceItf  livreService;
	
	@Autowired
	private EmpruntRepository empruntRepository;

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
	    utilisateurRepository.save(utilisateur);

	    // Génération du jeton
	    String token = UUID.randomUUID().toString();
	    VerificationToken verificationToken = new VerificationToken(token, utilisateur);
	    verificationTokenRepository.save(verificationToken); // Utiliser l'instance injectée

	    // Envoi de l'email
	    String verificationUrl = "http://localhost:8080/verifier-email?token=" + token;
	    String subject = "Confirmez votre email";
	    String body = "Bonjour " + utilisateur.getLogin() + ",\n\nCliquez sur ce lien pour vérifier votre email :\n" + verificationUrl;

	    // Utiliser le service d'email correctement injecté
	    emailService.sendSimpleMessage(utilisateur.getEmail(), subject, body);
	}
	@Override
	public boolean verifierEmail(String token) {
	    var optionalToken = verificationTokenRepository.findByToken(token); // Utiliser l'instance injectée

	    if (optionalToken.isPresent() && !optionalToken.get().isExpired()) {
	        Utilisateur utilisateur = optionalToken.get().getUtilisateur();
	        utilisateur.setVerified(true);
	        utilisateurRepository.save(utilisateur);
	        return true;
	    }
	    return false;
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
	public List<Emprunt> getEmpruntLivreList(Long idUtilisateur) {
		Utilisateur utilisateur = lireUtilisateurParId(idUtilisateur);
		System.out.println("UtilisateurService - getEmpruntLivreList utilisateur:" + utilisateur);
		return utilisateur.getEmprunterLivreList();
	}
	@Override
	public Emprunt getEmpruntById(Long id) {
		return empruntRepository.findById(id).get();
	}
	@Override
	public void emprunterListLivreUtilisateur(List<Long> livreIdList, Long idUtilisateur) {
		Utilisateur utilisateur = lireUtilisateurParId(idUtilisateur);
		List<Livre> livreList = livreService.getLivreEmprunterListParLivreIdList(livreIdList);
		System.out.println("UtilisateurService - emprunterListLivreUtilisateur livreList:\n" + livreList);
		System.out.println("majLivreEmprunterListUtilisateur utilisateur=" + utilisateur);
		Emprunt emprunt = null;
		for(int i=0; i < livreList.size(); i++) {
			emprunt = new Emprunt(livreList.get(i), new Date());
			empruntRepository.save(emprunt);
			utilisateur.emprunterLivre(emprunt);
		}
		System.out.println("majLivreEmprunterListUtilisateur utilisateur=" + utilisateur);
		utilisateurRepository.save(utilisateur);	
	}
	@Override
	public void majEmprunt(Emprunt emprunt) {
		empruntRepository.save(emprunt);
	}

}
