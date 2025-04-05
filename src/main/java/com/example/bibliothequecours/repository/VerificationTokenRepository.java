package com.example.bibliothequecours.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bibliothequecours.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}