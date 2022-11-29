package com.jin.blog.service;

import com.jin.blog.domain.CustomUser;
import com.jin.blog.domain.User;
import com.jin.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Service
@Transactional
public class CustomUserService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  public User findByUsername(String username) {
    Optional<User> account = userRepository.findByUsername(username);
    account.orElseThrow(() -> new EntityNotFoundException("EntityNotFound"));
    return account.get();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findByUsername(username);
    User.Auth auth = new User.Auth(user.getRole().name());
    return CustomUser.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .authorities(Arrays.asList(auth))
        .build();
  }
}
