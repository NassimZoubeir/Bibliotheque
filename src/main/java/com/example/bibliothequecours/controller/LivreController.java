package com.example.bibliothequecours.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.bibliothequecours.entity.Livre;
import com.example.bibliothequecours.service.LivreServiceItf;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;



@Controller
public class LivreController {
	@Autowired
	private  LivreServiceItf  livreService;
	
	@RequestMapping("/accueil")
	public  String  accueil()  {
		System.out.println("==== /accueils ====");
		return  "accueil";
	}
	@RequestMapping("/afficher-livres")
    public String administrer(Model model) {
		System.out.println("==== /afficher-livres ====");
		List<Livre> livreList = livreService.getAllLivre();
    	System.out.println("LivreList: " + livreList);
    	model.addAttribute("livreList", livreList);
        return "catalogue";
    }
	 @RequestMapping("/creer-livre")
	 public String creerLivre(Model model) {
	 model.addAttribute("titre", "Créer livre");
	 return "creer-livre";
	 }
	 @Autowired
	 private ServletContext context;
	 
	 @Value("${dir.images}")
	 private String imageDir;
	 
	 @RequestMapping("/creer-livre-validation")
	 public String creerValidationLivre(String titre, int nb, int nbPages, String descr, MultipartFile image) {
		 if(!image.isEmpty()) {
			 String couverture = image.getOriginalFilename();
			 String absolutePath = Paths.get(context.getRealPath("/"), "resources/images").toString();
			 String pathFile = imageDir + couverture;
		 try {
			 image.transferTo(new File(pathFile));
		 } catch (IllegalStateException | IOException e) {
			 e.printStackTrace();
		 }
		 livreService.creerLivre(new Livre(titre, nb, nbPages, couverture, descr));
		}
		return "redirect:/afficher-livres";
	}
	 @RequestMapping("/afficher-livre/{id}")
	 public String afficherLivre(@PathVariable Long id, Model model) {
		 System.out.println("==== /afficher-livre ====");
		 System.out.println("id=" + id);
		 Livre livre = livreService.getLivreById(id);
		 System.out.println("Livre=" + livre);
		 model.addAttribute("livre", livre);
		 model.addAttribute("titre", livre.getTitre());
		 return "detail";
	 }
	 @RequestMapping("/reserver/{id}")
	    public String emprunterLivre(@PathVariable Long id, Model model, HttpServletRequest request) {
	    	System.out.println("==== /reserver/" + id + " ====");
	    	List<Long> livreEmprunterListId = (List<Long>) request.getSession().getAttribute("livreEmprunterListId");
	    	if(livreEmprunterListId == null) {
	    		livreEmprunterListId = new ArrayList<>();
	    	}
	    	// si la liste ne contient pas déjà le livre
	    	if(!livreEmprunterListId.contains(id)) {
	    		livreEmprunterListId.add(id);
	    	}
	    	request.getSession().setAttribute("livreEmprunterListId", livreEmprunterListId);
	    	System.out.println("livreEmprunterListId=" + livreEmprunterListId);
	    	return "redirect:/afficher-panier";
	    }
	    @RequestMapping("/afficher-panier")
		public String afficherPanier(Model model, HttpServletRequest request) {
			System.out.println("==== /afficher-panier ====");
			List<Long> livreEmprunterListId = (List<Long>) request.getSession().getAttribute("livreEmprunterListId");
			System.out.println("livreEmprunterListId=" + livreEmprunterListId);
			if(livreEmprunterListId != null) {
				List<Livre> livreEmprunterList = livreService.getLivreEmprunterListParLivreIdList(livreEmprunterListId);
				model.addAttribute("livreEmprunterList", livreEmprunterList);
			}
			else System.out.println("Pas de livre emprunté");
			model.addAttribute("titre", "Réservation livre");
			return "panier";
		}

	 
}
