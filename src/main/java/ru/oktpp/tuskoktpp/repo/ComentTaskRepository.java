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
import ru.oktpp.tuskoktpp.entity.ComentTask;
import ru.oktpp.tuskoktpp.entity.Tasktab;

/**
 *
 * @author Danil
 */
public interface ComentTaskRepository extends CrudRepository<ComentTask, Long> {
    List<ComentTask> findByidtask(Tasktab idtask);
    @Query("FROM ComentTask where idtask = ?1 and countsled=?2")
    List<ComentTask> findByComentUser(Tasktab idtask, Integer countsled);
  
}
