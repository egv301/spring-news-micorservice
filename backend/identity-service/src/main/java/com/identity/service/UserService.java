package com.identity.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.identity.dto.RegistrationDto;
import com.identity.entity.User;
import com.identity.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleService roleService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	public Optional<User> findUserById(Long user_id) {
        return userRepository.findById(user_id);
    }

	public Iterable<User> findAll(){
        return userRepository.findAll();
    }
	
	public User findUserName(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("User '%s' not found", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public User createNewUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        if(registrationDto.isAdmin()) {
        	user.setRoles(List.of(roleService.getAdminRole()));
        }else {
        	user.setRoles(List.of(roleService.getUserRole()));
        }
        return userRepository.save(user);
    }
}