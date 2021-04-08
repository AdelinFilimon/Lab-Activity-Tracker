package com.gmail.filimon24.adelin.labactivitytracker.persistence;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Submission;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.SubmissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, SubmissionId> {
}
