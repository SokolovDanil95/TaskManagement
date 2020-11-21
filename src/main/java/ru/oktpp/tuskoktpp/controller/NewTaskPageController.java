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
import org.springframework.beans.factory.annotation.Autowired;


import ru.oktpp.tuskoktpp.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.oktpp.tuskoktpp.repo.TypeTaskRepository1;
import  ru.oktpp.tuskoktpp.repo.UserRepository;
import ru.oktpp.tuskoktpp.repo.TasktabRepository;
import ru.oktpp.tuskoktpp.repo.StateTaskRepository;
import ru.oktpp.tuskoktpp.repo.TaskUserRepository;
import ru.oktpp.tuskoktpp.repo.ProectRepository;
import ru.oktpp.tuskoktpp.repo.StagesProectRepository;
import ru.oktpp.tuskoktpp.repo.LinkStageProectRepository;

import ru.oktpp.tuskoktpp.entity.Tasktab;
import ru.oktpp.tuskoktpp.entity.AppUser;
import ru.oktpp.tuskoktpp.entity.TaskUser;
import ru.oktpp.tuskoktpp.entity.LinkStageProect;

@Controller
public class NewTaskPageController {

    @Autowired
    TypeTaskRepository1 repositoryTypeTask;
    @Autowired
    UserRepository repositoryUser;
    @Autowired
    TasktabRepository repositoryTasktab;
    @Autowired
    StateTaskRepository repositoryStateTask;
    @Autowired
    TaskUserRepository repositoryTaskUser;
    @Autowired
    ProectRepository ProectRepository;
    @Autowired
    StagesProectRepository StagesProectRepository;
    @Autowired
    LinkStageProectRepository LinkStageProectRepository;
    
    @RequestMapping(value = "/newTask", method = RequestMethod.GET)
    public String newTaskPage(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        
        model.addAttribute("TypeTask",repositoryTypeTask.findAll());
        model.addAttribute("User",repositoryUser.findAll());
        model.addAttribute("StateTask",repositoryStateTask.findAll());
        model.addAttribute("ProjectAll",ProectRepository.findAll());
        return "newTaskPage";
    }
    
    
    @RequestMapping(value = "/SaveNewTask", method = RequestMethod.POST)
    public String SaveNewTask(Model model, @RequestParam("SelectType") Long SelectType, @RequestParam("inputName") String inputName,
            @RequestParam("exampleTextarea") String exampleTextarea, @RequestParam("SelectDerectUser") Long SelectDerectUser,
            @RequestParam("SelectWorkUser") Long SelectWorkUser, @RequestParam("timeDo") Date timeDo, 
            @RequestParam("SelectTimeVrema") String SelectTimeVrema, @RequestParam("SostTask") Long SostTask, @RequestParam("SelectProject") Long idProject) throws ParseException {
 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateVipPlan = dateFormat.format( timeDo)+" "+SelectTimeVrema;
        Date dateVipoln=new SimpleDateFormat("yyyy-MM-dd hh.mm").parse(dateVipPlan);
        
        Tasktab newTask = repositoryTasktab.save(new Tasktab(inputName, exampleTextarea, repositoryTypeTask.findById(SelectType).get(),
        dateVipoln, new Date(), repositoryStateTask.findById(SostTask).get(), repositoryUser.findById(SelectDerectUser).get()));
        repositoryTaskUser.save(new TaskUser(repositoryUser.findById(SelectWorkUser).get(),newTask));
        
        if(idProject==null){
            return "redirect:/userInfo"; 
        } else{
            return "redirect:/selectionStageToTask?id="+idProject+"&task="+newTask.getId(); 
        }
    }
    
    @RequestMapping(value = "/selectionStageToTask", method = RequestMethod.GET)
    public String selectionStageToTask(Model model, Principal principal, @RequestParam("id") Long idProject, @RequestParam("task") Long idtask) {
        model.addAttribute("idtask", idtask);
        model.addAttribute("StagesPoject",StagesProectRepository.findByidproect(ProectRepository.findById(idProject).get()));
        return "selectionStageToTaskPage";
    }
    
    @RequestMapping(value = "/SaveLinkStageTask", method = RequestMethod.POST)
    public String SaveLinkStageTask(Model model, @RequestParam("SelectStage") Long SelectStage, @RequestParam("idtask") Long idtask){
        
        LinkStageProectRepository.save(new LinkStageProect(StagesProectRepository.findById(SelectStage).get(),repositoryTasktab.findById(idtask).get()));
       
        return "redirect:/userInfo"; 

    }
       
 }