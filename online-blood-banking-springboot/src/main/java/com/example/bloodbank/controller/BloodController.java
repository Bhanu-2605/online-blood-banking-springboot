package com.example.bloodbank.controller;

import com.example.bloodbank.model.BloodRequest;
import com.example.bloodbank.model.BloodStock;
import com.example.bloodbank.repository.BloodRequestRepository;
import com.example.bloodbank.repository.BloodStockRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/blood")
public class BloodController {
    private final BloodStockRepository stockRepo;
    private final BloodRequestRepository reqRepo;
    public BloodController(BloodStockRepository stockRepo, BloodRequestRepository reqRepo) {
        this.stockRepo = stockRepo; this.reqRepo = reqRepo;
    }

    @GetMapping("/stock")
    public String stock(Model m) {
        m.addAttribute("stocks", stockRepo.findAll());
        return "stock";
    }

    @GetMapping("/request")
    public String requestForm() { return "request"; }

    @PostMapping("/request")
    public String requestSubmit(@RequestParam Long userId, @RequestParam String bloodGroup, @RequestParam Integer unitsRequested, Model m) {
        BloodRequest br = new BloodRequest();
        br.setUserId(userId);
        br.setBloodGroup(bloodGroup);
        br.setUnitsRequested(unitsRequested);
        br.setStatus("PENDING");
        br.setRequestedAt(LocalDateTime.now());
        reqRepo.save(br);
        m.addAttribute("msg", "Request submitted. Admin will review.");
        return "request";
    }
}
