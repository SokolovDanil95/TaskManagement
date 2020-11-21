/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.repo;

import java.util.List;
import ru.oktpp.tuskoktpp.entity.AppRole;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Danil
 */
public interface RoleRepository extends CrudRepository<AppRole, Long> {

    List<AppRole> findByroleId(Long id);
    
}
