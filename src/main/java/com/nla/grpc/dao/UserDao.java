package com.nla.grpc.dao;

import com.nla.grpc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class UserDao implements JpaRepository<User,Long> {

}
