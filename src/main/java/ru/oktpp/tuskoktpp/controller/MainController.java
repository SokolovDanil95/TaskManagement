/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import ru.oktpp.tuskoktpp.repo.RoleRepository;
import ru.oktpp.tuskoktpp.repo.UserRepository;
import ru.oktpp.tuskoktpp.repo.LinkUserRoleRepository;

import ru.oktpp.tuskoktpp.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.oktpp.tuskoktpp.entity.AppRole;
import static ru.oktpp.tuskoktpp.utils.EncrytedPasswordUtils.encrytePassword;
import ru.oktpp.tuskoktpp.entity.AppUser;
import ru.oktpp.tuskoktpp.entity.UserRole;


@Controller
public class MainController {
    
    @Autowired
    RoleRepository repositoryRole;
    
    @Autowired
    UserRepository repositoryUser;
    
    @Autowired        
    LinkUserRoleRepository repositoryLinkUserRole;
            
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {

        return "redirect:/userInfo";
    }
    
    @RequestMapping(value = "/about" , method = RequestMethod.GET)
    public String AboutPage(Model model) {

        return "/about/about";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("VseUser",repositoryUser.findAll());
        return "/login";
    }
 
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        return "logoutSuccessfulPage";
    }
 
    
 
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = WebUtils.toString(loginedUser);
 
            model.addAttribute("userInfo", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "403Page";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model, Principal principal) {
 
        // After user login successfully.
        String userName = principal.getName();
        
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        String proverkaRole = WebUtils.toStringRole(loginedUser);
        model.addAttribute("provRole", proverkaRole);
        
        model.addAttribute("RoleFull", repositoryRole.findAll());
        
        return "/addUser";
    }
    
    	@RequestMapping(value = {"/saveUser"}, method = RequestMethod.POST)
	public String process(Model model,@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("password_control") String password_control, @RequestParam("idRole") int[] idRole){	
                
                
                
                String encrytedPassword=encrytePassword(password);
                
                repositoryUser.save(new AppUser(username,encrytedPassword, 1));
               
                List<AppUser> userId = repositoryUser.findByuserName(username);
                
                for( int i=0; i < idRole.length; i++)
                {
                    List<AppRole> roleId = repositoryRole.findByroleId((long)(idRole[i]));
                
                    repositoryLinkUserRole.save(new UserRole(userId.get(0),roleId.get(0)));
                }
                
                return "index";
	}
        
 }