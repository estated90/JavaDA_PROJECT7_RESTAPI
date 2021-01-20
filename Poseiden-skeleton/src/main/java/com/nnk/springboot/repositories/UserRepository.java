package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.User;



/**
 * @author nicolas
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    /**
     * @param userName User name of user in DB
     * @return the user as object
     */
    User findByUsername(String userName);
    /**
     * @param role Role of the user in DB
     * @return the user as object
     */
    User findByRole(String role);

}
