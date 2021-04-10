package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaboratoryClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer labNumber;

    @Column(nullable = false)
    @DateTimeFormat(pattern = CustomApplicationProperties.dateTimeFormat)
    private DateTime date;

    @Column(nullable = false, length = CustomApplicationProperties.smallFieldLen)
    private String title;

    @Column(length = CustomApplicationProperties.bigFieldLen)
    private String description;

    @OneToMany(mappedBy = "laboratoryClass", fetch = FetchType.LAZY)
    private List<Topic> topics;

    @OneToMany(mappedBy = "laboratoryClass", fetch = FetchType.LAZY)
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "laboratoryClass", fetch = FetchType.LAZY)
    private List<Attendance> attendances;

}
