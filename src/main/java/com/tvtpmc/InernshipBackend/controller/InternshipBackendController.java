package com.tvtpmc.InernshipBackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@CrossOrigin
@RequestMapping("/api/internshipBackendController")
public class InternshipBackendController {
	@GetMapping
	public String getString() {
		return "string";
	}
}

