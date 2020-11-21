/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.controller;

import java.security.Principal;
import java.util.ArrayList;
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

import ru.oktpp.tuskoktpp.entity.Tasktab;
import ru.oktpp.tuskoktpp.entity.AppUser;
import ru.oktpp.tuskoktpp.entity.TapeTaskCount;
import ru.oktpp.tuskoktpp.entity.TaskUser;
import ru.oktpp.tuskoktpp.entity.Typetask;
import ru.oktpp.tuskoktpp.entity.InformUserComent;
import ru.oktpp.tuskoktpp.repo.StateTaskRepository;

import ru.oktpp.tuskoktpp.repo.TasktabRepository;
import ru.oktpp.tuskoktpp.repo.UserRepository;
import ru.oktpp.tuskoktpp.repo.TaskUserRepository;
import ru.oktpp.tuskoktpp.repo.TypeTaskRepository1;
import ru.oktpp.tuskoktpp.repo.InformComentUserRepository;

@Controller
public class UserPageController {
    
    @Autowired
    TasktabRepository repositoryTasktab;
    
    @Autowired
    TaskUserRepository repositoryTaskUser;
    
    @Autowired
    UserRepository repositoryUser;
    
    @Autowired
    TypeTaskRepository1 repositoryTypeTask;
    
    @Autowired
    StateTaskRepository repositoryStateTask;
    
    @Autowired
    InformComentUserRepository repositoryInformComentUser;

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
        
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        String proverkaRole = WebUtils.toStringRole(loginedUser);
        model.addAttribute("provRole", proverkaRole);
        
        
        AppUser user = repositoryUser.findByuserName(userName).get(0);        
        model.addAttribute("TaskListDirect", repositoryTasktab.findBydirectortask(user));
        
        
        
        List<TaskUser> userTask = repositoryTaskUser.findByiduser(user);
        List<TaskUser> userTaskClose = new ArrayList<TaskUser>();
        for(int i=0; i<userTask.size(); i++){
            if(userTask.get(i).getIdtask().getState().getName().equals("закрыто")==true){
                userTaskClose.add(userTask.get(i));
                userTask.remove(i);
                i=i-1;
            }
        }
        if(userTask!=null){
            model.addAttribute("TaskListIspolnitel", userTask);
        }
        if(userTaskClose!=null){
            model.addAttribute("TaskListClose", userTaskClose);
        }
        
        List<InformUserComent> infUser = repositoryInformComentUser.findByiduser(user);
        model.addAttribute("countInfUser",infUser.size());
        return "userInfoPage";
    }
    
    @RequestMapping(value = "/KanbanDoskaPage", method = RequestMethod.GET)
    public String KanbanDoskaPage(Model model, Principal principal, @RequestParam("id") Long idTypeTask) {
         
        System.out.println("idTypeTask: " + idTypeTask);
        // After user login successfully.
        String userName = principal.getName();
        System.out.println("User Name: " + userName);
        AppUser user = repositoryUser.findByuserName(userName).get(0);        
        List<TaskUser> userTask = repositoryTaskUser.findByiduser(user);
        
        List<TapeTaskCount> TaskTypeVseCount = new ArrayList<TapeTaskCount>();
        
        Iterable<Typetask> type = repositoryTypeTask.findAll(); 
        Iterator<Typetask> it = type.iterator();
        while (it.hasNext()) {
            int contTask=0;
            Typetask element = it.next();   
            for(int j=0; j<userTask.size();j++){
                if(userTask.get(j).getIdtask().getTypetask()==element){
                    if(userTask.get(j).getIdtask().getState().getName().equals("закрыто")){
                        contTask=contTask;
                    }else{
                        contTask=contTask+1;
                    }
                }
            }
            TaskTypeVseCount.add(new TapeTaskCount(element,contTask));
        }
        
        for(int i=0;i<TaskTypeVseCount.size();i++){
            if(TaskTypeVseCount.get(i).getTapetask().getId()==idTypeTask){
                model.addAttribute("OneTypeTask",TaskTypeVseCount.get(i));
                TaskTypeVseCount.remove(i);
            }      
        }
        
        
        /*Iterable<Typetask> type = repositoryTypeTask.findAll(); 
        Iterator<Typetask> it = type.iterator();
        while (it.hasNext()) {
            Typetask element = it.next();   
            if(element.getId()==idTypeTask){
                TapeTaskCount oneTypeTaskC = new TapeTaskCount(element,userTask.size());
                model.addAttribute("OneTypeTask",oneTypeTaskC);
                it.remove();
            }
        }
        */
        model.addAttribute("TypeTask",TaskTypeVseCount);
        
        for(int i=0;i<userTask.size();i++){
            if(userTask.get(i).getIdtask().getTypetask().getId()!=idTypeTask){
                userTask.remove(i);
                i=i-1;
            }
        }
        
        List<TaskUser> userTaskClose = new ArrayList<TaskUser>();
        for(int i=0; i<userTask.size();i++){
            if(userTask.get(i).getIdtask().getState().getName().equals("закрыто")){
                userTaskClose.add(userTask.get(i));
                userTask.remove(i);
                i=i-1;
            }
        }
        List<TaskUser> userTaskVPlan = new ArrayList<TaskUser>();
        for(int i=0; i<userTask.size();i++){
            if(userTask.get(i).getIdtask().getState().getName().equals("ожидает")){
                userTaskVPlan.add(userTask.get(i));
                userTask.remove(i);
                i=i-1;
            }
        }
        List<TaskUser> TaskVRabote = new ArrayList<TaskUser>();
        for(int i=0; i<userTask.size();i++){
            if(userTask.get(i).getIdtask().getState().getName().equals("в работе")){
                TaskVRabote.add(userTask.get(i));
                userTask.remove(i);
                i=i-1;
            }
        }
        model.addAttribute("userTaskDrugoe",userTask);
        model.addAttribute("userTaskClose",userTaskClose);
        model.addAttribute("userTaskVPlan",userTaskVPlan);
        model.addAttribute("TaskVRabote",TaskVRabote);

        List<InformUserComent> infUser = repositoryInformComentUser.findByiduser(user);
        model.addAttribute("countInfUser",infUser.size());
        
        return "KanbanDoskaPage";
    }
    
    @RequestMapping(value = "/deleteTask", method = RequestMethod.GET)
    public String deleteTask(Model model, Principal principal, @RequestParam("id") Long idTask) {
        
        System.out.println("Удалить задачу");
        List<TaskUser> userTask =  repositoryTaskUser.findByidtask(repositoryTasktab.findById(idTask).get());
        repositoryTaskUser.deleteAll(userTask);
        repositoryTasktab.delete(repositoryTasktab.findById(idTask).get());
        return "redirect:/userInfo";
    }
    
    
       
 }