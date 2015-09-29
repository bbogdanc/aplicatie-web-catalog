package com.bogdan.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by bbaloi on 30.07.2015.
 */

@Controller
public class LogoutController {

    @RequestMapping( value = "/logout", method = RequestMethod.GET)
    public ModelAndView dogetLogout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("home");
    }

    @RequestMapping( value = "/logout", method = RequestMethod.POST)
    public ModelAndView doLogout(HttpSession session) {
        session.invalidate();
        return new ModelAndView("home");
    }

}
