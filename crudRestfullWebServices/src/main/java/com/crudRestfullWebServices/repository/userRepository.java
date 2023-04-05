package com.crudRestfullWebServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudRestfullWebServices.enitity.user;

@Repository
public interface userRepository extends JpaRepository<user, Long>{

}
