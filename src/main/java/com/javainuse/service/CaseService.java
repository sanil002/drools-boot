package com.javainuse.service;

import java.util.Collection;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.casemanagement.CaseResponce;
import demo.casemanagement.Chargeback;

@Service
public class CaseService {

	private final KieContainer kieContainer;

	@Autowired
	public CaseService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}

	public CaseResponce getProductDiscount(Chargeback chargeback) {
		CaseResponce caseResponse =new CaseResponce();
		KieSession kieSession = kieContainer.newKieSession("session2");		
		kieSession.insert(chargeback);		
		int rulesCount=kieSession.fireAllRules();
		System.out.println("Number of rules executed :==> " +rulesCount);
		
		 Collection<FactHandle> allHandles = kieSession.getFactHandles();
		 
	        for (FactHandle handle : allHandles) {
	            if (kieSession.getObject(handle) instanceof CaseResponce){	            	
	            	caseResponse = (CaseResponce) kieSession.getObject(handle);
	            }
	        }	        
	        kieSession.dispose();
	        
		return caseResponse;
	}
}