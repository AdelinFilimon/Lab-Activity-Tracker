package com.gmail.filimon24.adelin.labactivitytracker.persistence;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
