package com.bogdan.app.controllers;

import com.bogdan.app.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by bbaloi on 28.07.2015.
 */

@Controller
public class StudentLoginController {

    @Autowired
    private StudentService studentService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView displayLogin(HttpSession session) {
        if (session != null && session.getAttribute("Username") != null) {
            ModelAndView model = new ModelAndView("/student");
            model.addObject("student", studentService.getStudent(
                    (String) session.getAttribute("Username")));

            return model;
        }

        return new ModelAndView("studentlogin");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loggedInOptions(@RequestParam("Username") String Username,
                                        @RequestParam("Password") String Password,
                                        HttpSession session) {
        boolean flag;
        ModelAndView modelAndView;


            flag = studentService.isValid(Username, Password);

            if (flag == true) {
                modelAndView = new ModelAndView("student");
                modelAndView.addObject("student", studentService.getStudent(Username));
                session.setAttribute("Username", Username);
                return modelAndView;
            } else {
                modelAndView = new ModelAndView("studentlogin");
                modelAndView.addObject("incorectlogin", "incorect");
                return modelAndView;
            }
        }

}
