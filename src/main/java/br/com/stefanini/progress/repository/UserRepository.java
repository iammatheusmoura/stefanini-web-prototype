package br.com.stefanini.progress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stefanini.progress.model.User;

@Repository("userNameRepository")
public interface UserRepository extends JpaRepository<User, Long>{	
	User findByCpf(String cpf);
	User findByUsername(String username);
}
