package com.bogdan.app.controllers;

import com.bogdan.app.dataaccessobjects.StudentDAO;
import com.bogdan.app.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by bbaloi on 29.07.2015.
 */

@Controller
public class GradesController {

    @Autowired
    private StudentService studentService;

    @RequestMapping( value = "/grades", method = RequestMethod.GET)
    public ModelAndView getGrades(HttpSession session){
        if(session != null && session.getAttribute("Username") != null){
            ModelAndView modelAndView = new ModelAndView("grades");
            modelAndView.addObject("info",
                    studentService.getGrades((String) session.getAttribute("Username")));
            return modelAndView;
        }
        else{
            ModelAndView modelAndView = new ModelAndView("studentlogin");
            modelAndView.addObject("unauthenticated","unauthenticated");
            return modelAndView;
        }
    }

    @RequestMapping( value = "/grades" , method = RequestMethod.POST)
    public ModelAndView seeGrades(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("grades");
        modelAndView.addObject("info",
                studentService.getGrades((String) session.getAttribute("Username")));
        return modelAndView;
    }

}
