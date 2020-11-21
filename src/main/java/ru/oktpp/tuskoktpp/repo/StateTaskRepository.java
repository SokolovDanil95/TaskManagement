/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.repo;

import ru.oktpp.tuskoktpp.entity.Statetask;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import ru.oktpp.tuskoktpp.entity.AppUser;

/**
 *
 * @author Danil
 */
public interface StateTaskRepository extends CrudRepository<Statetask, Long> {
    
  
}
