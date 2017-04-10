package com.triquetra.datetime.controller;

/**
 * Created by sayef on 10/19/16.
 */

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class DateTimeSampleController {


    @CrossOrigin
    @RequestMapping(value = "/datetime", method = RequestMethod.GET)
    public String detail(Model model){
        return "index";
    }

}
