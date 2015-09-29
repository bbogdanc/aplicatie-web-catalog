package com.bogdan.app.controllers;

import com.bogdan.app.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by bbaloi on 31.07.2015.
 */

@Controller
public class TeacherLoginController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/tlogin" , method = RequestMethod.GET)
    public ModelAndView checkGettLogin(HttpSession session) {
       if(session != null && session.getAttribute("teachId") != null) {
           Integer Id = (Integer) session.getAttribute("teachId");
           ModelAndView modelAndView = new ModelAndView("thomepage");
           modelAndView.addObject("teacher",teacherService.getTeacher(Id));
           return modelAndView;
       }
       else{
           return new ModelAndView("home");
       }
    }

    @RequestMapping(value = "/tlogin", method = RequestMethod.POST)
    public ModelAndView checktLogin(@RequestParam("LastName")String LastName,
                                    @RequestParam("FirstName")String FirstName,
                                    @RequestParam("Id")Integer Id,
                                    @RequestParam("Password")String Password,
                                    HttpSession session){

        ModelAndView model = null;

            boolean flag = teacherService.isValid(Id, LastName, FirstName, Password);

            if (flag == true) {
                model = new ModelAndView("thomepage");
                model.addObject("teacher",teacherService.getTeacher(Id));
                session.setAttribute("teachId", Id);

            } else {
                model = new ModelAndView("teachlogin");
                model.addObject("incorrectlogin", "Invalid credentials");
            }
        return model;
    }


}
