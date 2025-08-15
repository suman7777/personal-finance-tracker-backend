package com.suman.finance.personal_finance_backend.controller;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suman.finance.personal_finance_backend.dto.DashboardResponse;
import com.suman.finance.personal_finance_backend.service.DashboardService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {

	private final DashboardService dashboardService;

	public DashboardController(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	@GetMapping("/dashboard-data")
	public DashboardResponse getDashboardData() {
		return dashboardService.getDashboardData();
	}
}
