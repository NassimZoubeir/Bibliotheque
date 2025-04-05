package com.example.bibliothequecours;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.bibliothequecours.entity.Auteur;
import com.example.bibliothequecours.entity.Editeur;
import com.example.bibliothequecours.entity.Emprunt;
import com.example.bibliothequecours.entity.Livre;
import com.example.bibliothequecours.entity.Utilisateur;
import com.example.bibliothequecours.outil.Outil;
import com.example.bibliothequecours.repository.AuteurRepository;
import com.example.bibliothequecours.repository.EditeurRepository;
import com.example.bibliothequecours.repository.EmpruntRepository;
import com.example.bibliothequecours.repository.LivreRepository;
import com.example.bibliothequecours.repository.UtilisateurRepository;
import com.example.bibliothequecours.service.EmailServiceImpl;




@SpringBootApplication
public class BibliothequecoursApplication {
	private  static LivreRepository  livreRepository  =  null;
	private static UtilisateurRepository utilisateurRepository = null;
	private static EmpruntRepository empruntRepository = null;
	private static AuteurRepository auteurRepository = null;
	private static EditeurRepository editeurRepository = null;
	private static EmailServiceImpl emailService = null;
	public static void main(String[] args) {
		ApplicationContext  ctx  = SpringApplication.run(BibliothequecoursApplication.class, args);
		livreRepository  =  ctx.getBean(LivreRepository.class);
		utilisateurRepository = ctx.getBean(UtilisateurRepository.class);
		empruntRepository = ctx.getBean(EmpruntRepository.class);
		auteurRepository = ctx.getBean(AuteurRepository.class);
		editeurRepository = ctx.getBean(EditeurRepository.class);
		emailService = ctx.getBean(EmailServiceImpl.class);
		initialiser();
	}
	public  static  void  initialiser()  {
		Livre  livre1  =  new  Livre("Les  quatre  accords",  1,  1602,  "tolteques.jpg",  "Castaneda  a  fait  découvrir  ...");
		Livre  livre2  =  new  Livre("Saturne",  2,  466,  "saturne.jpg",  "Sa  fille  est  encore  un  bébé  quand  ...");
		livreRepository.save(livre1);
		livreRepository.save(livre2);
		String hashPassword;
		Utilisateur utilisateur = null;
		try {
			hashPassword = Outil.hashMdpSha256("nass");
			utilisateur = new Utilisateur("nass", hashPassword, "nass@gmail.com", "abonne");
			utilisateurRepository.save(utilisateur);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Impossible de créer l'utilisateur Nass");
		}
		 try {
			 hashPassword = Outil.hashMdpSha256("admin");
			 utilisateur = new Utilisateur("admin", hashPassword, "arnacoeur@gmail.com", "administrateur");
			 utilisateurRepository.save(utilisateur);
		} catch (NoSuchAlgorithmException e) {
			 System.out.println("Impossible de créer l'utilisateur Admnistrateur");
		}
		Emprunt emprunt = new Emprunt(livre1, new Date()); 
		empruntRepository.save(emprunt);
		utilisateur.emprunterLivre(emprunt);
		utilisateurRepository.save(utilisateur);
		
		Auteur auteur = new Auteur("Melissa Da Costa", "Mélissa Da Costa est une romancière française. Après des études d’économie et de gestion, elle est chargée de communication dans le domaine de l’énergie et du climat. Elle suit également des formations en aromathérapie, naturopathie et sophrologie. La lecture fait partie de sa routine quotidienne, au milieu du sport et de l’écriture. Elle est l’autrice des Lendemains et de Tout le bleu du ciel, son premier roman porté par les libraires, salué par la presse, et couronné par le jury du Grand Prix du Livre de Poche 2020.");		
		auteurRepository.save(auteur);
		livre1.setAuteur(auteur);
		livreRepository.save(livre1);
		
		Editeur editeur = new Editeur("Les Éditions Flammarion", "https://www.coollibri.com/blog/10-maisons-edition-populaires-2018/#2_Les_Editions_Flammarion");
		editeurRepository.save(editeur);
		livre1.setEditeur(editeur);
		livreRepository.save(livre1);
	
	//	emailService.sendSimpleMessage("zoubeirnassim@gmail.com", "Email Test Bibliotheque", "Bonjour Nassim ce mail vient de ton application Bibliotheque");

	}
	
}
