package com.example.bibliothequecours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bibliothequecours.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	Utilisateur findByLogin(String login);
}