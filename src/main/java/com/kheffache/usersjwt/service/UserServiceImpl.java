package com.kheffache.usersjwt.service;

import com.kheffache.usersjwt.entities.Role;
import com.kheffache.usersjwt.entities.User;
import com.kheffache.usersjwt.repository.RoleRepository;
import com.kheffache.usersjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

        // pour le cryptage des motsdepass avant enregistrement
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User usr = userRepository.findByUsername(username);
        Role role = roleRepository.findByRole(rolename);

        usr.getRoles().add(role);
       // userRepository.save(usr); AVEC @Transactional / A chaque modification le sevice lance un save
        return usr;
    }

    @Override
    public List<User> fidAllUsers() {
        return userRepository.findAll();
    }
}
