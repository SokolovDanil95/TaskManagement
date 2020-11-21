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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.oktpp.tuskoktpp.repo.StateTaskRepository;
import ru.oktpp.tuskoktpp.repo.ProectRepository;

import ru.oktpp.tuskoktpp.entity.Proect;



@Controller
public class newProjectPageController {
    
    @Autowired
    StateTaskRepository StateTaskRepository;
    @Autowired
    ProectRepository ProectRepository;
            
    @RequestMapping(value = "/newProjectPage", method = RequestMethod.GET)
    public String newProjectPage(Model model, Principal principal) {
        
        model.addAttribute("state",StateTaskRepository.findAll());
        
        return "newProjectPage";
    }
   
    @RequestMapping(value = "/SaveNewProject", method = RequestMethod.POST)
    public String SaveNewProject(Model model, @RequestParam("inputName") String inputName,
            @RequestParam("exampleTextarea") String exampleTextarea, @RequestParam("timeRun") Date timeRun, 
            @RequestParam("SelectTimeRun") String SelectTimeRun, @RequestParam("timeDo") Date timeDo, 
            @RequestParam("SelectTimeEnd") String SelectTimeEnd, @RequestParam("SostTask") Long SostTask) throws ParseException {
 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateRun = dateFormat.format( timeRun)+" "+SelectTimeRun;
        Date dateNachRab=new SimpleDateFormat("yyyy-MM-dd hh.mm").parse(dateRun);
        String dateVipPlan = dateFormat.format( timeDo)+" "+SelectTimeEnd;
        Date dateVipoln=new SimpleDateFormat("yyyy-MM-dd hh.mm").parse(dateVipPlan);
        
        ProectRepository.save(new Proect(inputName, exampleTextarea, dateNachRab, dateVipoln, StateTaskRepository.findById(SostTask).get(),0));
        
        return "redirect:/ProjectPage";
    }
    
 }