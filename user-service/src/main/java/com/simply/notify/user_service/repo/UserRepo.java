package com.simply.notify.user_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simply.notify.user_service.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
