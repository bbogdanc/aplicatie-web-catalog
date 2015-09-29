package com.bogdan.app.controllers;

import com.bogdan.app.core.StudentwithGrades;
import com.bogdan.app.dataaccessobjects.TeacherDAO;
import com.bogdan.app.services.TeacherService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by bbaloi on 03.08.2015.
 */

@Controller
public class ListParticipantsController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/allstudentgrades", method = RequestMethod.GET)
    public ModelAndView listParticipants(HttpSession session){
        if(session != null && session.getAttribute("teachId") != null) {
            return new ModelAndView("coursepage");
        }else{
            return new ModelAndView("home");
        }
    }

    @RequestMapping(value = "/allstudentgrades", method = RequestMethod.POST)
    public ModelAndView listParticipants(@RequestParam("C_id")Integer id,
                                         HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("courseparticipants");
        List<StudentwithGrades> list = teacherService.getStudentAtCourse((Integer)session.getAttribute("teachId"),id);
        modelAndView.addObject("info", list);
        return modelAndView;
    }
}
