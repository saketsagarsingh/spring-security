package com.security.springSecurity.service;

import com.security.springSecurity.model.Student;
import com.security.springSecurity.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    StudentRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = repo.findByName(username);

        if(student == null)
            throw new UsernameNotFoundException("User not found");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        student.setPassword(encoder.encode(student.getPassword()));
        return student;
    }
}
