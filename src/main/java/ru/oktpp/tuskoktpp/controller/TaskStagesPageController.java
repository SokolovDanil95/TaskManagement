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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.oktpp.tuskoktpp.repo.StateTaskRepository;
import ru.oktpp.tuskoktpp.repo.StagesProectRepository;
import ru.oktpp.tuskoktpp.repo.ProectRepository;
import ru.oktpp.tuskoktpp.entity.Proect;
import ru.oktpp.tuskoktpp.entity.StagesProect;
import ru.oktpp.tuskoktpp.entity.TaskUser;
import ru.oktpp.tuskoktpp.entity.Tasktab;
import ru.oktpp.tuskoktpp.entity.LinkStageProect;
import ru.oktpp.tuskoktpp.repo.TaskUserRepository;
import ru.oktpp.tuskoktpp.repo.TasktabRepository;
import ru.oktpp.tuskoktpp.repo.TypeTaskRepository1;
import ru.oktpp.tuskoktpp.repo.UserRepository;
import ru.oktpp.tuskoktpp.repo.LinkStageProectRepository;
import ru.oktpp.tuskoktpp.repo.StagesProectRepository;



@Controller
public class TaskStagesPageController {
    
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
    LinkStageProectRepository LinkStageProectRepository;
    @Autowired
    StagesProectRepository StagesProectRepository;
    
    @Autowired
    ProectRepository ProectRepository;
            
    @RequestMapping(value = "/newTaskInProjectStagePage", method = RequestMethod.GET)
    public String newTaskInProjectStagePage(Model model, Principal principal, @RequestParam("id") Long idStage) {
        
                // After user login successfully.
        String userName = principal.getName();

        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        
        model.addAttribute("TypeTask",repositoryTypeTask.findAll());
        model.addAttribute("User",repositoryUser.findAll());
        model.addAttribute("StateTask",repositoryStateTask.findAll());
        
        model.addAttribute("idStage",idStage);
        
        return "newTaskInProjectStagePage";
    }
    
    @RequestMapping(value = "/SaveNewTaskLinkStage", method = RequestMethod.POST)
    public String SaveNewTaskLinkStage(Model model, @RequestParam("SelectType") Long SelectType, @RequestParam("inputName") String inputName,
            @RequestParam("exampleTextarea") String exampleTextarea, @RequestParam("SelectDerectUser") Long SelectDerectUser,
            @RequestParam("SelectWorkUser") Long SelectWorkUser, @RequestParam("timeDo") Date timeDo, 
            @RequestParam("SelectTimeVrema") String SelectTimeVrema, @RequestParam("SostTask") Long SostTask, @RequestParam("idStage") Long idStage) throws ParseException {
 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateVipPlan = dateFormat.format( timeDo)+" "+SelectTimeVrema;
        Date dateVipoln=new SimpleDateFormat("yyyy-MM-dd hh.mm").parse(dateVipPlan);
        
        Tasktab newTask = repositoryTasktab.save(new Tasktab(inputName, exampleTextarea, repositoryTypeTask.findById(SelectType).get(),
        dateVipoln, new Date(), repositoryStateTask.findById(SostTask).get(), repositoryUser.findById(SelectDerectUser).get()));
        repositoryTaskUser.save(new TaskUser(repositoryUser.findById(SelectWorkUser).get(),newTask));
        StagesProect stage = StagesProectRepository.findById(idStage).get();
        LinkStageProectRepository.save(new LinkStageProect(stage,newTask));
        
        return "redirect:/detailedProjectPage?id=" + stage.getIdproect().getId(); 
 
    }
    
    @RequestMapping(value = "/LinkStageTaskPage", method = RequestMethod.GET)
    public String LinkStageTaskPage(Model model, Principal principal,@RequestParam("id") Long idStage) {
        
        model.addAttribute("idStage",idStage);
        model.addAttribute("AllTask", repositoryTasktab.findAll());
        
        return "LinkStageTaskPage";
    }
    
    @RequestMapping(value = {"/SaveLinkTaskStageIzProject"}, method = RequestMethod.POST)
    public String SaveLinkTaskStageIzProject(Model model,@RequestParam("idTask") Long[] idTask, @RequestParam("id") Long idStage){	
        
        StagesProect stage = StagesProectRepository.findById(idStage).get();
                
        for(int i=0;i<idTask.length;i++){
            LinkStageProectRepository.save(new LinkStageProect(stage,repositoryTasktab.findById(idTask[i]).get()));
        }
        
        consworkdone(stage.getIdproect().getId());
        
        return "redirect:/detailedProjectPage?id=" + stage.getIdproect().getId();
    }
    
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