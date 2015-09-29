package com.bogdan.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bbaloi on 28.07.2015.
 */
@Controller
public class InitController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(){
        return "home";
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public ModelAndView redirect(@RequestParam("Status") String status){
        if(status.equals("Student")){
            return new ModelAndView("studentlogin");
        }
        else if(status.equals("Teacher")){
            return new ModelAndView("teachlogin");
        }

       return null;

    }

}
