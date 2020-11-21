/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.oktpp.tuskoktpp.entity.AppUser;
import ru.oktpp.tuskoktpp.entity.InformUserComent;
import ru.oktpp.tuskoktpp.repo.InformComentUserRepository;

import ru.oktpp.tuskoktpp.repo.ProectRepository;
import ru.oktpp.tuskoktpp.repo.UserRepository;
import ru.oktpp.tuskoktpp.repo.StagesProectRepository;

import ru.oktpp.tuskoktpp.entity.StagesProect;
import ru.oktpp.tuskoktpp.entity.Proect;


@Controller
public class ProjectPageController {
    
    @Autowired
    ProectRepository ProectRepository;
    
    @Autowired
    UserRepository repositoryUser;
    
    @Autowired
    InformComentUserRepository repositoryInformComentUser;
    
    @Autowired
    StagesProectRepository StagesProectRepository;
            
    @RequestMapping(value = "/ProjectPage", method = RequestMethod.GET)
    public String ProjectPage(Model model, Principal principal) {
        
        String userName = principal.getName();
        AppUser user = repositoryUser.findByuserName(userName).get(0);    
        List<InformUserComent> infUser = repositoryInformComentUser.findByiduser(user);
        model.addAttribute("countInfUser",infUser.size());
        
        model.addAttribute("project", ProectRepository.findAll());
        return "ProjectPage";
    }
    
    @RequestMapping(value = "/detailedProjectPage", method = RequestMethod.GET)
    public String detailedProjectPage(Model model, Principal principal, @RequestParam("id") Long idProject) {
        Proect projectvibor = ProectRepository.findById(idProject).get();
        List <StagesProect> stages = StagesProectRepository.findByidproect(projectvibor);
        model.addAttribute("projectvibor", projectvibor);
        if(stages.size()!=0){
            model.addAttribute("StagesProectOne", stages.get(0));
            model.addAttribute("StagesProectOst", stages.subList(1, stages.size()));
        }
        return "detailedProjectPage";
    }
    
   
    
 }