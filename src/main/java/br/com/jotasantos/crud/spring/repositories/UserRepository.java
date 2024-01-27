package br.com.jotasantos.crud.spring.repositories;

import br.com.jotasantos.crud.spring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
