package com.bogdan.app.controllers;

import com.bogdan.app.core.Teacher;
import com.bogdan.app.dataaccessobjects.TeacherDAO;
import com.bogdan.app.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by bbaloi on 31.07.2015.
 */

@Controller
public class CourseController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value= "/courses",method = RequestMethod.GET)
    public ModelAndView seeGradesg(HttpSession session){
        if(session != null && session.getAttribute("teachId") != null){
            ModelAndView model = new ModelAndView("coursepage");
            model.addObject("teacher",teacherService.getTeacher((Integer) session.getAttribute("teachId")));
            model.addObject("courses",teacherService.getPopulation((Integer) session.getAttribute("teachId")));
            return model;
        }
        else{
            return new ModelAndView("home");
        }
    }

    @RequestMapping(value= "/courses",method = RequestMethod.POST)
    public ModelAndView seeGrades(HttpSession session){
        ModelAndView model = new ModelAndView("coursepage");
        model.addObject("teacher",teacherService.getTeacher((Integer) session.getAttribute("teachId")));
        model.addObject("courses",teacherService.getPopulation((Integer) session.getAttribute("teachId")));
        return model;
    }
}
