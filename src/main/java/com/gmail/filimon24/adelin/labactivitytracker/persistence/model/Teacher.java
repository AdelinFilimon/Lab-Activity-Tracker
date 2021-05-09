package com.gmail.filimon24.adelin.labactivitytracker.persistence.model;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = CustomApplicationProperties.SMALL_FIELD_LEN, unique = true)
    private String username;

    @Column(nullable = false, length = CustomApplicationProperties.BIG_FIELD_LEN, unique = true)
    private String email;

    @Column(nullable = false, length = CustomApplicationProperties.PASSWORD_FIELD_LEN)
    private String password;

    @Column(nullable = false, length = CustomApplicationProperties.SMALL_FIELD_LEN)
    private String firstName;

    @Column(nullable = false, length = CustomApplicationProperties.SMALL_FIELD_LEN)
    private String lastName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(CustomApplicationProperties.TEACHER_ROLE_IDENTIFIER));
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
