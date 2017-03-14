package br.com.stefanini.progress.service;

import br.com.stefanini.progress.model.User;

public interface UserService {	
	public User findUserByUsername(String username);
	public User findByCpf(String cpf);
	public void saveUser(User user);

}
