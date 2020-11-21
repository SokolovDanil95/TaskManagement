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

import ru.oktpp.tuskoktpp.entity.Tasktab;


@Controller
public class informationUserPageController {
    @Autowired
    UserRepository repositoryUser;
    
    @Autowired
    InformComentUserRepository repositoryInformComentUser;
    
    @RequestMapping(value = "/informUserComentPage", method = RequestMethod.GET)
    public String informUserComentPage(Model model, Principal principal) {
        String userName = principal.getName();

        AppUser user = repositoryUser.findByuserName(userName).get(0);  
        model.addAttribute("detailedInformComrnt", repositoryInformComentUser.findByiduser(user));    
        
        List<InformUserComent> infUser = repositoryInformComentUser.findByiduser(user);
        model.addAttribute("countInfUser",infUser.size());

        return "informUserComentPage";
    }
    
    @RequestMapping(value = "/delInformUserComent", method = RequestMethod.GET)
    public String delInformUserComent(Model model, Principal principal, @RequestParam("id") Long idTypeInfCom) {
        repositoryInformComentUser.deleteById(idTypeInfCom);
        return "redirect:/informUserComentPage";
    }
    
    @RequestMapping(value = "/DelVseInfComUser", method = RequestMethod.GET)
    public String DelVseInfComUser(Model model, Principal principal) {
        String userName = principal.getName();
        AppUser user = repositoryUser.findByuserName(userName).get(0); 
        repositoryInformComentUser.deleteAll(repositoryInformComentUser.findByiduser(user));
        return "redirect:/informUserComentPage";
    }
    

       
 }