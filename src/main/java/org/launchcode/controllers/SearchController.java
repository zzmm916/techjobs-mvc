package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        if (searchType.equals("all")) {
            if (searchTerm=="null") {
                ArrayList<HashMap<String, String>> jobs = JobData.findAll();
//                String[] displayColumns={"position type","name","employer","location","core competency"};
                model.addAttribute("results", jobs.size() + " Result(s)");
                model.addAttribute("column", columnChoices);
                model.addAttribute("jobs", jobs);
            }else {
                ArrayList<HashMap<String, String>> jobs = JobData.findByValue(searchTerm);
                model.addAttribute("results", jobs.size() + " Result(s)");
                model.addAttribute("columns", columnChoices);
                model.addAttribute("jobs", jobs);
            }
        } else {

            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//            model.addAttribute("title", "result(s)");
            model.addAttribute("results", jobs.size() + " Result(s)");
            model.addAttribute("jobs", jobs);
            model.addAttribute("columns", columnChoices);

        }
        return "search";
    }

}
