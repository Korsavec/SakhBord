package com.sakhbord.bord.controllers.get.rules;

import com.sakhbord.bord.models.rules.Rules;
import com.sakhbord.bord.repository.service.ServiceJpa;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class RulesController {


    private final
    ServiceJpa serviceJpa;

    public RulesController(ServiceJpa serviceJpa) {
        this.serviceJpa = serviceJpa;
    }
    @GetMapping(value = "/rules", produces = "application/json")
    public List<Rules> getRules() {

        return serviceJpa.findOneRules();

    }


}
