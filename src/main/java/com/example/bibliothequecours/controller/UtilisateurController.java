package com.example.bibliothequecours.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.bibliothequecours.entity.Utilisateur;
import com.example.bibliothequecours.outil.Outil;
import com.example.bibliothequecours.service.UtilisateurServiceItf;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public  class  UtilisateurController  {
	
	@Autowired
	private UtilisateurServiceItf utilisateurService;

	@GetMapping("/creer-compte")
	public  String  creerUtilisateur()  {
		return  "creer-utilisateur";
	}
	
	@RequestMapping("/creer-compte-validation")
	public  String  creerUtilisateurValidation(String  login,  String  password,  String  mail)  {
		System.out.println(login  +  ",  "  +  password  +  ",  "  +  mail);
		String  hashPassword  =  null;
		try  {
			hashPassword  =  Outil.hashMdpSha256(password);
		}  catch  (NoSuchAlgorithmException  e)  {
			System.out.println("ERREUR  -  fonction  hashMdpSha256");
		}
		
		Utilisateur  utilisateur  =  new  Utilisateur(login,  hashPassword,  mail,  "abonne");
		utilisateurService.creerUtilisateur(utilisateur);
		return  "login";
	}
	@RequestMapping("/login")
	public  String  login(Model  model)  {
		return  "login";
	}
	@RequestMapping("/login-validation")
    public String login(String login, String password, Model model, HttpServletRequest request) {
    	System.out.println("==== login-validation ====");
    	System.out.println(login + " / " + password);
    	String hashPassword = null;
		try {
			hashPassword = Outil.hashMdpSha256(password);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("ERREUR - fonction hashMdpSha256");
		}
    	System.out.println("hashPassword=" + hashPassword);
    	Utilisateur utilisateur = utilisateurService.lireUtilisateurParLogin(login);
    	System.out.println("utilisateur:" + utilisateur);
    	if(utilisateur.getPasswdHash().equals(hashPassword)) {
    		System.out.println("Vous êtes connecté");
    		request.getSession().setAttribute("id",  utilisateur.getId());
			request.getSession().setAttribute("login",  utilisateur.getLogin());
			request.getSession().setAttribute("role",  utilisateur.getRole());
    	}
    	else System.out.println("Vous n'êtes pas connecté");
    	return "accueil";
    }
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("====  /logout  ====");
		request.getSession().invalidate();
		return "accueil";
	 }
	
}
