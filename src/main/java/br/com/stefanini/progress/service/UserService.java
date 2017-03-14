package br.com.stefanini.progress.service;

import br.com.stefanini.progress.model.User;

public interface UserService {	
	public User findLoginByUsername(String username);
	public User findByCpf(String cpf);
	public void saveUser(User user);

}
