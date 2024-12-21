package com.example.bibliothequecours.service;

import java.util.List;

import com.example.bibliothequecours.entity.Livre;


public interface LivreServiceItf {
	List<Livre> getAllLivre();
	void creerLivre(Livre livre);
	Livre getLivreById(Long id);
	List<Livre> getLivreEmprunterListParLivreIdList(List<Long> livreEmprunterListId);
}
