package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import lombok.*;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = CustomApplicationProperties.SMALL_FIELD_LEN)
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = CustomApplicationProperties.DATE_TIME_FORMAT)
    private DateTime deadline;

    @Column(length = CustomApplicationProperties.BIG_FIELD_LEN)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private LaboratoryClass laboratoryClass;

    @OneToMany(mappedBy = "assignment", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Submission> submissions;

}
