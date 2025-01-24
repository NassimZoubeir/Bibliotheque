package com.example.bibliothequecours.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Auteur {
	@Id
	@GeneratedValue
	private Long id;
	private String nom;
	@Lob
	private String bibliographie;
	public Auteur() {}
	public Auteur(String nom, String bibliographie) {
		this.nom = nom;
		this.bibliographie = bibliographie;
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
	public String getBibliographie() {
		return bibliographie;
	}
	public void setBibliographie(String bibliographie) {
		this.bibliographie = bibliographie;
	}
	@Override
	public String toString() {
		return "Auteur [id=" + id + ", nom=" + nom + /* ", bibliographie=" + bibliographie + */ "]";
	}
	
	
	
	

}
