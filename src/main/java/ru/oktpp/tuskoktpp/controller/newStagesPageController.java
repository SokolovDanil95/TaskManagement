/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.oktpp.tuskoktpp.entity.LinkStageProect;

import ru.oktpp.tuskoktpp.repo.StateTaskRepository;
import ru.oktpp.tuskoktpp.repo.StagesProectRepository;
import ru.oktpp.tuskoktpp.repo.ProectRepository;
import ru.oktpp.tuskoktpp.entity.Proect;
import ru.oktpp.tuskoktpp.entity.StagesProect;



@Controller
public class newStagesPageController {
    
    @Autowired
    StateTaskRepository StateTaskRepository;
    @Autowired
    StagesProectRepository StagesProectRepository;
    @Autowired
    ProectRepository ProectRepository;
            
    @RequestMapping(value = "/newStagePage", method = RequestMethod.GET)
    public String newStagePage(Model model, Principal principal, @RequestParam("id") Long idProect) {
        
        model.addAttribute("state",StateTaskRepository.findAll());
        model.addAttribute("idproject",idProect);
        
        return "newStagePage";
    }
    
    @RequestMapping(value = "/SaveNewStage", method = RequestMethod.POST)
    public String SaveNewStage(Model model, @RequestParam("inputName") String inputName,
            @RequestParam("exampleTextarea") String exampleTextarea, @RequestParam("timeRun") Date timeRun, 
            @RequestParam("SelectTimeRun") String SelectTimeRun, @RequestParam("timeDo") Date timeDo, 
            @RequestParam("SelectTimeEnd") String SelectTimeEnd, @RequestParam("SostTask") Long SostTask, @RequestParam("idproject") Long idproject) throws ParseException {
 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateRun = dateFormat.format( timeRun)+" "+SelectTimeRun;
        Date dateNachRab=new SimpleDateFormat("yyyy-MM-dd hh.mm").parse(dateRun);
        String dateVipPlan = dateFormat.format( timeDo)+" "+SelectTimeEnd;
        Date dateVipoln=new SimpleDateFormat("yyyy-MM-dd hh.mm").parse(dateVipPlan);
        
        StagesProectRepository.save(new StagesProect(inputName, exampleTextarea, dateNachRab, dateVipoln, StateTaskRepository.findById(SostTask).get(),0, ProectRepository.findById(idproject).get()));
        
        consworkdone(idproject);
        
        return "redirect:/detailedProjectPage?id="+idproject;
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