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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Furqan
 */
@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/company", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Company>> listAllCompanies() {
        return new ResponseEntity<>(companyService.listCompanies(), HttpStatus.OK);
    }
}
