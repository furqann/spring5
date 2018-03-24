/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resolve.api.controller;

import com.resolve.api.model.Company;
import com.resolve.api.service.CompanyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Furqan
 */
@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/company", method = RequestMethod.GET, 
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Company>> listAllCompanies() {
        return new ResponseEntity<>(companyService.listCompanies(), HttpStatus.OK);
    }

    @GetMapping(value="/company/{id}" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getCompany(@PathVariable("id") long id){
        if(companyService.findById(id) == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok().body(companyService.findById(id));
    }
    
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public ResponseEntity<Void> createCompany(@RequestBody Company company, UriComponentsBuilder ucBuilder) {
        //Check does the company has all parameters which has to be added.
        
        //Check Does it all already exist in the database.
        
        companyService.save(company);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/company/{id}").buildAndExpand(company.getId()).toUri());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/company/{id}",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateCompany(@PathVariable("id") long id, @RequestBody Company company){
        
        if(companyService.findById(id) == null)
            return ResponseEntity.notFound().build();
        
        companyService.update(id, company); 
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping(value="/company/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") long id){
        if(companyService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        companyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
