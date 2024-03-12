package rs.ac.uns.ftn.springsecurityexample.service;

import java.util.List;

import rs.ac.uns.ftn.springsecurityexample.dto.UserData;
import rs.ac.uns.ftn.springsecurityexample.dto.UserRequest;
import rs.ac.uns.ftn.springsecurityexample.dto.UserUpdateDTO;
import rs.ac.uns.ftn.springsecurityexample.model.User;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
	User save(UserRequest userRequest);
	List<User> getAllPending();
	User acceptUser(long userId);
	User denyUser(long userId, String denyReason);
	UserData updateUser(UserUpdateDTO userUpdateDTO);
}
