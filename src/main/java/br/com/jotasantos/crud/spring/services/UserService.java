package br.com.jotasantos.crud.spring.services;

import br.com.jotasantos.crud.spring.entities.User;
import br.com.jotasantos.crud.spring.exceptions.EntityNotFoundException;
import br.com.jotasantos.crud.spring.repositories.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User userDel = optionalUser.get();
            userDel.setAtivo(false);
            userDel.setDeleteddAt(LocalDateTime.now());
            return userDel;
        }

        throw new EntityNotFoundException(String.format("User of #%id", id));
    }

    public void update(Long id, User user) {
    }
}
