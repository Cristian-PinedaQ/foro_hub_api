package com.example.foro_hub_api.domain.UsersApi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository extends JpaRepository<UserApi, Long> {
    UserDetails findByLogin(String login);
}
