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

    @Column(length = CustomApplicationProperties.smallFieldLen, unique = true)
    private String username;

    @Column(nullable = false, length = CustomApplicationProperties.bigFieldLen, unique = true)
    private String email;

    @Column(length = CustomApplicationProperties.passwordFieldLen)
    private String password;

    @Column(nullable = false, length = CustomApplicationProperties.smallFieldLen)
    private String firstName;

    @Column(nullable = false, length = CustomApplicationProperties.smallFieldLen)
    private String lastName;

    @Column(length = CustomApplicationProperties.smallFieldLen)
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
        return Collections.singletonList(new SimpleGrantedAuthority(CustomApplicationProperties.studentRoleIdentifier));
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
