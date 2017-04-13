package com.triquetra.datetime.controller;

/**
 * Created by sayef on 10/19/16.
 */

import com.triquetra.datetime.core.DateTimeHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.TimeZone;

@RequestMapping("/timeField")
@RestController
public class DateTimeSampleController {


    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(HttpServletRequest request, Model model){
        String timeFromServer = "10.04.2017 23:59:59 AM";
        DateTimeHandler dateTimeHandler = new DateTimeHandler();

        String timeOnly = dateTimeHandler.splitDateTime(timeFromServer, "12", false)[0];
        model.addAttribute("timeField", timeOnly);
        return "index";
    }

    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("timeField")  String timeField,
                         Model model,
                         TimeZone timeZone,
                         Locale locale){

        DateTimeHandler dateTimeHandler = new DateTimeHandler();
        String timeOnly = null;
        try {
            timeOnly = dateTimeHandler.concatDateTime("10.12.2017", timeField, timeZone, "12", false);
        }catch (Exception e){
            System.out.println("Could not process timeField");
        }
        System.out.println(timeField + " " + timeOnly);
        return "index";
    }

}
