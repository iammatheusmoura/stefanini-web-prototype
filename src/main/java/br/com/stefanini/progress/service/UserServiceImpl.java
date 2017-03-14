package br.com.stefanini.progress.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stefanini.progress.model.Role;
import br.com.stefanini.progress.model.User;
import br.com.stefanini.progress.repository.RoleRepository;
import br.com.stefanini.progress.repository.UserRepository;

@Service("userService")
class UserServiceImpl implements UserService {	

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User findLoginByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByCpf(String cpf) {
		return userRepository.findByCpf(cpf);
	}
	
	@Override
	public void saveUser(User user) {
		user.setName(user.getName());
		user.setUsername(user.getUsername());
		user.setCpf(user.getCpf());
		user.setEmail(user.getEmail());
		user.setPassword(user.getPassword());
		user.setActive(true);		
		Role userRole = roleRepository.findByRole("ADMIN");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		
//		Project project = new Project();
//		project.setDescProject("Cielo - BOB");
//		user.setProject(new HashSet<Project>(Arrays.asList(project)));
	}

}
