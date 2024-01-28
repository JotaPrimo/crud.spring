package br.com.jotasantos.crud.spring.services;

import br.com.jotasantos.crud.spring.entities.User;
import br.com.jotasantos.crud.spring.repositories.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = false)
    public List<User> findAllSorted() {
        Sort sort = Sort.by(Sort.Order.asc("name"));
        return userRepository.findAll(sort);
    }

    @Transactional(readOnly = false)
    public User store(User user) {
        return userRepository.save(user);
    }

}
