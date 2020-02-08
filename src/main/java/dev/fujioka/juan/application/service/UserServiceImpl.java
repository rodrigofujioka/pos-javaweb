package dev.fujioka.juan.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fujioka.juan.domain.model.user.User;
import dev.fujioka.juan.domain.service.UserService;
import dev.fujioka.juan.infrastructure.repository.UserRepository;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public UserRepository getRepository() {
		return repository;
	}

	
}
