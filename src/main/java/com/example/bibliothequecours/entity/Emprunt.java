package com.example.bibliothequecours.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Emprunt {
	@Id
	@GeneratedValue
	private Long id;
	private Date empruntDate;
	private Date retourDate;
	@ManyToOne
	private Livre livre;
	
	public Emprunt() {}

	public Emprunt(Livre livre, Date enpruntDate) {
		this.livre = livre;
		this.empruntDate = enpruntDate;
	}

	@Override
	public String toString() {
		return "Emprunt [id=" + id + " , enpruntDate=" + empruntDate + ", retourDate=" + retourDate + ", livre=" + livre + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEnpruntDate() {
		return empruntDate;
	}

	public void setEnpruntDate(Date enpruntDate) {
		this.empruntDate = enpruntDate;
	}

	public Date getRetourDate() {
		return retourDate;
	}

	public void setRetourDate(Date retourDate) {
		this.retourDate = retourDate;
	}

	public Livre getLivre() {
		return livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}
}
