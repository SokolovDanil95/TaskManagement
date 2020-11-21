/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.repo;

import ru.oktpp.tuskoktpp.entity.Tasktab;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import ru.oktpp.tuskoktpp.entity.AppUser;
import ru.oktpp.tuskoktpp.entity.Typetask;

/**
 *
 * @author Danil
 */
public interface TasktabRepository extends CrudRepository<Tasktab, Long> {
    
    List<Tasktab> findBydirectortask(AppUser id);
    List<Tasktab> findBytypetask(Typetask id);
    Tasktab findByid(Long id);
    

}
