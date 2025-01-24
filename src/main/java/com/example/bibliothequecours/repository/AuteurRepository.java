package com.example.bibliothequecours.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bibliothequecours.entity.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {}
