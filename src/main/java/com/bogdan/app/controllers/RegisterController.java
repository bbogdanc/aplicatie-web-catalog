package com.bogdan.app.controllers;

import com.bogdan.app.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpSession;

/**
 * Created by bbaloi on 04.08.2015.
 */

@Controller
public class RegisterController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/registerstud", method= RequestMethod.POST)
    public ModelAndView registerStudent(@RequestParam(value = "LastName") String LastName,
                        @RequestParam(value = "FirstName") String FirstName,
                        @RequestParam(value = "FatherInitial") String FatherInitial,
                        @RequestParam(value = "Username") String Username,
                        @RequestParam(value = "Password") String Password,
                        @RequestParam(value = "Course") String Course,HttpSession session){

        ModelAndView modelAndView;
        boolean flag = teacherService.validateRegister(LastName,FirstName,
                FatherInitial,Username,Password);
        if(flag == false) {
            modelAndView=new ModelAndView("studentregister");
            modelAndView.addObject("incorrectregister","1");
            return modelAndView;
        }
        else {
            Integer teacherId = (Integer) session.getAttribute("teachId");
            Integer courseId = teacherService.getCourseID(teacherId,Course);
            teacherService.addStudent(LastName,FirstName,
                    FatherInitial,Username,Password,courseId,teacherId);
            modelAndView=new ModelAndView("registersuccess");
            return modelAndView;
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerForm() {
        return new ModelAndView("studentregister");
    }
}
