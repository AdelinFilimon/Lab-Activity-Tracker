package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Column(nullable = false, length = 64)
    private String email;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(nullable = false, length = 16)
    private String firstName;

    @Column(nullable = false, length = 16)
    private String lastName;

    @Column(nullable = false, length = 16)
    private String hobby;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private StudentGroup studentGroup;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Submission> submissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("Student"));
    }

    @Override
    public String getUsername() {
        return email;
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
