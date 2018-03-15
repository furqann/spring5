/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resolve.api.controller;

import com.resolve.api.model.Domain;
import com.resolve.api.service.DomainService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author furqan.ahmad
 */
@RestController
public class DomainController {

    @Autowired
    DomainService domainServiceDao;

    //GET DOMAIN LIST
    @RequestMapping(value = "/domain", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Domain>> listAllDomains() {
        return new ResponseEntity<>(domainServiceDao.getDomains(), HttpStatus.OK);
    }

    //GET DOMAIN BY ID
    @RequestMapping(value = "/domain/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Domain> getDomain(@PathVariable("id") long id) {
        Domain domain = domainServiceDao.findById(id);

        if (domain == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(domain, HttpStatus.OK);
    }

    //POST DOMAIN
    @RequestMapping(name = "/domain", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> createDomain(@RequestBody Domain domain,  UriComponentsBuilder ucBuilder) {
//        if (userService.isUserExist(user)) {
//            System.out.println("A User with name " + user.getName() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
        domainServiceDao.save(domain);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/domain/{id}").buildAndExpand(domain.getId()).toUri());
        
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
