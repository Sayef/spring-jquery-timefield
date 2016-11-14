package com.triquetra.datetime.usage.controller;

/**
 * Created by sayef on 10/19/16.
 */

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.tigerit.nlp.dialogue.agent.FrogDialogue;
import com.tigerit.nlp.model.Expression;
import com.tigerit.nlp.intent.maker.IntentTrainer;
import com.tigerit.nlp.intent.maker.RegexMap;
import com.tigerit.nlp.model.RequestExpression;
import com.tigerit.nlp.solrj.analytics.Synonym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class DateTimeSampleController {


    @CrossOrigin
    @RequestMapping(value = "/datetime", method = RequestMethod.GET)
    public String detail(Model model){
        return "index";
    }

}
