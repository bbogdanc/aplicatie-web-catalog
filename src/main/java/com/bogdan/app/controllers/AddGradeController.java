package com.bogdan.app.controllers;

import com.bogdan.app.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by bbaloi on 05.08.2015.
 */

@Controller
public class AddGradeController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value="/addgrade", method = RequestMethod.POST)
    public ModelAndView addGrade(@RequestParam("course")String course,
                         @RequestParam("username")String username,
                         @RequestParam("grade")Integer nota,
                         HttpSession session) {
        Integer teacherId = (Integer) session.getAttribute("teachId");
        teacherService.addGrade(username,course,teacherId,nota);
        return new ModelAndView("successgrade");
    }

    @RequestMapping(value="/gradeform", method=RequestMethod.POST)
    public ModelAndView goToForm() {
        return new ModelAndView("addgrade");
    }
}
