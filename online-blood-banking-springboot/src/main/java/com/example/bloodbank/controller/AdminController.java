package com.example.bloodbank.controller;

import com.example.bloodbank.model.BloodStock;
import com.example.bloodbank.model.BloodRequest;
import com.example.bloodbank.repository.BloodRequestRepository;
import com.example.bloodbank.repository.BloodStockRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final BloodStockRepository stockRepo;
    private final BloodRequestRepository reqRepo;
    public AdminController(BloodStockRepository stockRepo, BloodRequestRepository reqRepo) {
        this.stockRepo = stockRepo; this.reqRepo = reqRepo;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model m) {
        m.addAttribute("stocks", stockRepo.findAll());
        m.addAttribute("requests", reqRepo.findByStatus("PENDING"));
        return "admin-dashboard";
    }

    @PostMapping("/stock/add")
    public String addStock(@RequestParam String bloodGroup, @RequestParam Integer units) {
        BloodStock bs = stockRepo.findByBloodGroup(bloodGroup).orElse(new BloodStock(bloodGroup,0));
        bs.setUnitsAvailable(bs.getUnitsAvailable() + units);
        stockRepo.save(bs);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/request/{id}/approve")
    public String approve(@PathVariable Long id) {
        BloodRequest r = reqRepo.findById(id).orElseThrow();
        r.setStatus("APPROVED");
        reqRepo.save(r);
        // reduce stock
        stockRepo.findByBloodGroup(r.getBloodGroup()).ifPresent(s -> {
            s.setUnitsAvailable(Math.max(0, s.getUnitsAvailable() - r.getUnitsRequested()));
            stockRepo.save(s);
        });
        return "redirect:/admin/dashboard";
    }
}
