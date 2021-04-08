package com.gmail.filimon24.adelin.labactivitytracker.persistence;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
