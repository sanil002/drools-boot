package com.javainuse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.service.CaseService;

import demo.casemanagement.CaseResponce;
import demo.casemanagement.Chargeback;

@RestController
public class CaseController {
	
private final CaseService caseService;
	
	@Autowired
	public CaseController(CaseService caseService) {
		this.caseService = caseService;
	}	
	
	@RequestMapping(value = "/case/state", method = RequestMethod.GET, produces = "application/json")
	public CaseResponce getCaseState() {
		Chargeback chargeback =new Chargeback(1L,15,"01");		
		return caseService.getProductDiscount(chargeback);		
	}


}
