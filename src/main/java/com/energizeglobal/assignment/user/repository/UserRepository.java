package com.energizeglobal.assignment.user.repository;

import com.energizeglobal.assignment.user.domain.User;
import org.springframework.data.repository.Repository;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 * Spring Data JPA Repository for {@link User} entity.
 */
public interface UserRepository extends Repository<User, Long> {

    User findById(Long id);

}
