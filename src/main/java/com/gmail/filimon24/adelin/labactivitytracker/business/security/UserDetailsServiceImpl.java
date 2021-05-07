package com.gmail.filimon24.adelin.labactivitytracker.business.security;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = teacherRepository.findTeacherByUsername(username);
        if (userDetails != null) return userDetails;

        userDetails = studentRepository.findStudentByUsername(username);
        if (userDetails == null) throw new UsernameNotFoundException(username);

        return userDetails;
    }
}
