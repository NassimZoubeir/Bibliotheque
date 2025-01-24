package com.example.bibliothequecours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.bibliothequecours.entity.Livre;


public interface LivreRepository extends JpaRepository<Livre, Long>  {
	@Modifying
	@Transactional
	@Query("UPDATE Livre l SET l.nbExemplaire = l.nbExemplaire - 1 WHERE l.id = :id")
	void decrementerNbExemplaireLivre(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Livre l SET l.nbExemplaire = l.nbExemplaire + 1 WHERE l.id = :id")
	void incrementerNbExemplaireLivre(@Param("id") Long id);
}
