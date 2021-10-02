package com.energizeglobal.assignment.user.service;

import com.energizeglobal.assignment.user.domain.User;

/**
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
public interface UserService {

    User findOne(Long id);

}
