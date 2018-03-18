/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resolve.api.service;

import com.resolve.api.model.Company;
import java.util.List;

/**
 *
 * @author Furqan
 */
public interface CompanyService {
    public List<Company> listCompanies();
    public Company findById();
    public void save(Company company);
    public void update(long id, Company company);
    public void delete(long id);
}
