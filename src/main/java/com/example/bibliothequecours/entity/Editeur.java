package com.example.bibliothequecours.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Editeur {
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	private String lienDescription;
	public Editeur() {}
	public Editeur(String nom, String lienDescription) {
		this.nom = nom;
		this.lienDescription = lienDescription;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLienDescription() {
		return lienDescription;
	}
	public void setLienDescription(String lienDescription) {
		this.lienDescription = lienDescription;
	}
	@Override
	public String toString() {
		return "Editeur [id=" + id + ", nom=" + nom + ", lienDescription=" + lienDescription + "]";
	}
	
	
	
}
