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
import ru.oktpp.tuskoktpp.entity.Proect;

/**
 *
 * @author Danil
 */
public interface ProectRepository extends CrudRepository<Proect, Long> {

}
