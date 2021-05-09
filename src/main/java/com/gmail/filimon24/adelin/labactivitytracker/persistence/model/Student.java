package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = CustomApplicationProperties.SMALL_FIELD_LEN, unique = true)
    private String username;

    @Column(nullable = false, length = CustomApplicationProperties.BIG_FIELD_LEN, unique = true)
    private String email;

    @Column(length = CustomApplicationProperties.PASSWORD_FIELD_LEN)
    private String password;

    @Column(nullable = false, length = CustomApplicationProperties.SMALL_FIELD_LEN)
    private String firstName;

    @Column(nullable = false, length = CustomApplicationProperties.SMALL_FIELD_LEN)
    private String lastName;

    @Column(length = CustomApplicationProperties.SMALL_FIELD_LEN)
    private String hobby;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private StudentGroup studentGroup;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Submission> submissions;

    @OneToOne(mappedBy = "student")
    @ToString.Exclude
    private Token token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(CustomApplicationProperties.STUDENT_ROLE_IDENTIFIER));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
