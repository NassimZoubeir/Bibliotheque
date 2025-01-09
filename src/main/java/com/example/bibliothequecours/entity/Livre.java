package com.example.bibliothequecours.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Livre {
	private static int NB_TITRE_CAR = 22;
	private static int NB_DESCR_CAR = 120;
	@Id
	@GeneratedValue
	private Long id;
	private String titre;
	@Transient
	private String subTitre;
	private int nbExemplaire;
	private int nbPages;
	private String image;
	@Lob
	private String description;
	@Transient
	private String subDescription;
	
	 private LocalDate dateEmprunt;
	 private LocalDate dateRetourPrevue; 
	
	public Livre() {}
	public Livre(String titre, int nbExemplaire, int nbPages, String image, String description) {
		//this.id = id;
		this.titre = titre;
		this.subTitre = Livre.subStringGenerique(titre, NB_TITRE_CAR);
		this.nbExemplaire = nbExemplaire;
		this.nbPages = nbPages;
		this.image = image;
		this.description = description;
		this.subDescription = Livre.subStringGenerique(description, NB_DESCR_CAR);
	}
	public static String subStringGenerique(String inStr, int nbCar) {
		String subString = "";
		if (inStr.length() < nbCar) {
			String complement = "";
			for(int i=0; i < nbCar - inStr.length(); i++) complement += ' ';
			//System.out.println("complement:" + complement);
			subString = inStr + complement;
			//System.out.println(subString);
		}
		else subString = inStr.substring(0, nbCar) + "...";
		return subString;
	}
	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", nbExemplaire=" + nbExemplaire + ", nbPages=" + nbPages + ", image=" + image
				+ /*", description=" + description + */ "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubTitre() {
		return subTitre;
	}
	public String getSubDescription() {
		return subDescription;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getNbExemplaire() {
		return nbExemplaire;
	}
	public void setNbExemplaire(int nbExemplaire) {
		this.nbExemplaire = nbExemplaire;
	}
	public int getNbPages() {
		return nbPages;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }
	
	   
}
