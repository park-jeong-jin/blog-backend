package com.jin.blog.service;

import com.jin.blog.domain.User;
import com.jin.blog.dto.UserDto;
import com.jin.blog.repository.UserRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

  @Autowired
  UserRepository userRepository;
  @Autowired
  PasswordEncoder passwordEncoder;

  public User findById(Long id) {
    Optional<User> account = userRepository.findById(id);
    account.orElseThrow(() -> new EntityNotFoundException("EntityNotFound"));
    return account.get();
  }

  public User findByUsername(String username) {
    Optional<User> account = userRepository.findByUsername(username);
    account.orElseThrow(() -> new EntityNotFoundException("EntityNotFound"));
    return account.get();
  }

  public List<UserDto.UserResponse> findAll(UserDto.UserFilter dto) {
    List<User> users = userRepository.findAll();
    return users.stream().map(User::toDto).collect(Collectors.toList());
  }

  public Long create(UserDto.UserRequest dto) {
    User user = dto.toEntity(passwordEncoder);
    if (ObjectUtils.isEmpty(user.getRole())) user.setRole(User.Role.NORMAL);
    user.setActivated(true);
    userRepository.save(user);
    return user.getId();
  }

  public Long update(UserDto.UserRequest dto) {
    return null;
  }

  public void delete(Long id) {
    User user = findById(id);
    userRepository.delete(user);
  }

  public User login(UserDto.UserLoginRequest dto) throws Exception {
    User user = findByUsername(dto.getUsername());
    if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) throw new Exception("UserPasswordNotMatched");
    return user;
  }
}
