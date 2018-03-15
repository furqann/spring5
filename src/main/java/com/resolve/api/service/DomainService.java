/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resolve.api.service;

import com.resolve.api.model.Domain;
import java.util.List;

/**
 *
 * @author furqan.ahmad
 */
public interface DomainService {
    List<Domain> getDomains();
    Domain findById(long id);
    void save(Domain domain);
    void update(long id, Domain domain);
    void delete(long id);
}
