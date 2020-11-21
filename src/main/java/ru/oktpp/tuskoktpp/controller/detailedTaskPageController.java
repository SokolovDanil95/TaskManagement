/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


import ru.oktpp.tuskoktpp.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.oktpp.tuskoktpp.entity.AppUser;
import ru.oktpp.tuskoktpp.repo.StateTaskRepository;
import ru.oktpp.tuskoktpp.repo.TaskUserRepository;
import ru.oktpp.tuskoktpp.repo.TasktabRepository;
import ru.oktpp.tuskoktpp.repo.UserRepository;
import ru.oktpp.tuskoktpp.repo.ComentTaskRepository;
import ru.oktpp.tuskoktpp.entity.ComentTask;
import ru.oktpp.tuskoktpp.repo.InformComentUserRepository;
import ru.oktpp.tuskoktpp.entity.InformUserComent;
import ru.oktpp.tuskoktpp.entity.LinkStageProect;
import ru.oktpp.tuskoktpp.entity.Proect;
import ru.oktpp.tuskoktpp.entity.StagesProect;

import ru.oktpp.tuskoktpp.entity.Tasktab;
import ru.oktpp.tuskoktpp.repo.ProectRepository;
import ru.oktpp.tuskoktpp.repo.StagesProectRepository;


@Controller
public class detailedTaskPageController {
    
    @Autowired
    TasktabRepository repositoryTasktab;
    
    @Autowired
    TaskUserRepository repositoryTaskUser;
    
    @Autowired
    UserRepository repositoryUser;
    
    @Autowired
    ComentTaskRepository repositoryComentTask;
    
    @Autowired
    StateTaskRepository repositoryStateTask;
    
    @Autowired
    InformComentUserRepository repositoryInformComentUser;
    
    @RequestMapping(value = "/detailedTaskPage", method = RequestMethod.GET)
    public String detailedTaskPage(Model model, Principal principal, @RequestParam("id") Long idTask) {
        System.out.println("ID задачи: " + idTask);
        Tasktab task = repositoryTasktab.findById(idTask).get();
        model.addAttribute("detailedTask", task);    
        model.addAttribute("conttask",repositoryComentTask.findByComentUser(task,0));
        model.addAttribute("istorComent",repositoryComentTask.findByComentUser(task,1));
        
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        
        model.addAttribute("provUser", loginedUser);
        return "detailedTaskPage";
    }
    
    @RequestMapping(value = "/detailedComentTask", method = RequestMethod.GET)
    public String detailedComentTask(Model model, Principal principal, @RequestParam("id") Long idTask, @RequestParam("idComent") Long idComent) {
        repositoryComentTask.deleteById(idComent);
        return "redirect:/detailedTaskPage?id="+idTask;
    }
    
    @RequestMapping(value = "/saveComentPage", method = RequestMethod.POST)
    public String saveComentPage(Model model, Principal principal, @RequestParam("id") Long idTask, @RequestParam("exampleTextarea") String comentTask) {
        String userName = principal.getName();
        AppUser user = repositoryUser.findByuserName(userName).get(0); 
        Tasktab task = repositoryTasktab.findById(idTask).get();
        ComentTask newComent = repositoryComentTask.save(new ComentTask(task, user, comentTask ,new Date(),0));
        AppUser ispolnTask = task.getTaskUserCollection().iterator().next().getIduser();
        if(user == ispolnTask){
        }else{
            repositoryInformComentUser.save(new InformUserComent(newComent,ispolnTask,"Новый коментарий к задачи!"));
        }
        if(user == task.getDirectortask()){
        }else{
            repositoryInformComentUser.save(new InformUserComent(newComent,task.getDirectortask(),"Новый коментарий к задачи!"));
        }
        return "redirect:/detailedTaskPage?id="+idTask;
    }
    
    @RequestMapping(value = "/closedTask", method = RequestMethod.GET)
    public String closedTask(Model model, Principal principal, @RequestParam("id") Long idTask) {
        System.out.println("ID задачи: " + idTask);
        model.addAttribute("StateTask",repositoryStateTask.findAll());
        model.addAttribute("idTask",idTask);
        return "closedTaskPage";
    }
    
    @RequestMapping(value = "/saveClosedTask", method = RequestMethod.POST)
    public String saveClosedTask(Model model, Principal principal, @RequestParam("id") Long idTask, 
            @RequestParam("SostTask") Long SostTask , @RequestParam("inputEvaluation") Double inputEvaluation) {
        System.out.println("ID задачи: " + idTask);
        System.out.println("SostTask: " + SostTask);
        System.out.println("inputEvaluation: " + inputEvaluation);
        Tasktab changeTask = repositoryTasktab.findById(idTask).get();
        changeTask.setState(repositoryStateTask.findById(SostTask).get());
        changeTask.setEvaluation(inputEvaluation);
        changeTask.setEnddate(new Date());
        repositoryTasktab.save(changeTask);
        String userName = principal.getName();
        AppUser user = repositoryUser.findByuserName(userName).get(0); 
        ComentTask newIstorComent = repositoryComentTask.save(new ComentTask(changeTask, user, 
                "Изменил состояние: "+changeTask.getState().getName() ,new Date(),1));

        AppUser ispolnTask = changeTask.getTaskUserCollection().iterator().next().getIduser();
        if(user == ispolnTask){
        }else{
            repositoryInformComentUser.save(new InformUserComent(newIstorComent,ispolnTask,"Изменили состояние у задачи!"));
        }
        if(user == changeTask.getDirectortask()){
        }else{
            repositoryInformComentUser.save(new InformUserComent(newIstorComent,changeTask.getDirectortask(),"Изменили состояние у задачи!"));
        }
        
        Iterable<LinkStageProect> taskStage = changeTask.getLinkStageProectCollection();
        if(taskStage!=null){
            Iterator<LinkStageProect> it = taskStage.iterator();
            while (it.hasNext()) {
                LinkStageProect element = it.next(); 
                consworkdone(element.getIdstage().getIdproect().getId());
            }
        }     
        
        
        return "InfoClosedTaskPage";
    }
    
    @Autowired
    StagesProectRepository StagesProectRepository;
    @Autowired
    ProectRepository ProectRepository;
    
    public void consworkdone(Long idproject){
        Proect pr = ProectRepository.findById(idproject).get();
        List<StagesProect> stagePr = StagesProectRepository.findByidproect(pr);
        int contTask=0;
        int endTask=0;
        for(int i=0; i<stagePr.size();i++){
            Iterable<LinkStageProect> taskStage = stagePr.get(i).getLinkStageProectCollection();
            if(taskStage!=null){
                Iterator<LinkStageProect> it = taskStage.iterator();
                while (it.hasNext()) {
                    LinkStageProect element = it.next(); 
                    if(element.getIdtask().getState().getName().equals("закрыто")){
                        endTask=endTask+1;
                    }
                    contTask = contTask+1;      
                }
            }
        }
        int procWork = (100/contTask)*endTask;
        pr.setReadiness(procWork);
        ProectRepository.save(pr);
    }
 }