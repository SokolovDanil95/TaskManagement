/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.repo;

import ru.oktpp.tuskoktpp.entity.UserRole;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Danil
 */
public interface LinkUserRoleRepository extends CrudRepository<UserRole, Long> {
}
