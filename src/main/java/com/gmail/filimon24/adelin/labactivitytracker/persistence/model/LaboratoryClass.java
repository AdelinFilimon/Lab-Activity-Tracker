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
@ToString
public class LaboratoryClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer labNumber;

    @Column(nullable = false)
    @DateTimeFormat(pattern = CustomApplicationProperties.DATE_TIME_FORMAT)
    private DateTime date;

    @Column(nullable = false, length = CustomApplicationProperties.MEDIUM_FIELD_LEN)
    private String title;

    @Column(length = CustomApplicationProperties.BIG_FIELD_LEN)
    private String description;

    @OneToMany(mappedBy = "laboratoryClass", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Topic> topics;

    @OneToMany(mappedBy = "laboratoryClass", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "laboratoryClass", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Attendance> attendances;

}
