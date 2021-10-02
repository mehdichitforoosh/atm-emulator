package com.energizeglobal.assignment.user.service;

import com.energizeglobal.assignment.user.domain.User;
import com.energizeglobal.assignment.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * User service for interacting with user domain.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find user by id
     *
     * @param id user id
     * @return user
     * @throws EmptyResultDataAccessException if user doesn't exist
     */
    @Override
    @Transactional(readOnly = true)
    public User findOne(Long id) {
        logger.info("find one user by " + id);
        User user = userRepository.findById(id);
        if (user != null) {
            return user;
        }
        throw new EmptyResultDataAccessException("user is empty.", 1);
    }
}
