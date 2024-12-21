package com.example.bibliothequecours.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliothequecours.entity.Livre;
import com.example.bibliothequecours.repository.LivreRepository;


@Service
public class LivreService implements LivreServiceItf{
	@Autowired
	private LivreRepository livreRepository;

	@Override
	public List<Livre> getAllLivre() {
		return livreRepository.findAll();
	}
	@Override
	 public void creerLivre(Livre livre) {
	 livreRepository.save(livre);
	 }
	@Override
	 public Livre getLivreById(Long id) {
	 Livre livre = livreRepository.findById(id).get();
	 return livre;
	 }
	@Override
	public List<Livre> getLivreEmprunterListParLivreIdList(List<Long> livreEmprunterListId){
    	List<Livre> livreEmprunterList = new ArrayList<>();
		for(int i=0; i < livreEmprunterListId.size(); i++) {
			livreEmprunterList.add(getLivreById(livreEmprunterListId.get(i)));
		}
		return livreEmprunterList;
    }

}
