/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.repo;

import ru.oktpp.tuskoktpp.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Danil
 */
public interface UserRepository extends CrudRepository<AppUser, Long> {
    
    List<AppUser> findByuserName(String userName);
    
    
    
}
