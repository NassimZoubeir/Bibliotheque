package com.example.bibliothequecours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bibliothequecours.entity.Emprunt;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {}
