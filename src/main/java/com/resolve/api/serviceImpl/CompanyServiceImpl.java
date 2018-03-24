/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resolve.api.serviceImpl;

import com.resolve.api.model.Company;
import com.resolve.api.service.CompanyService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Furqan
 */
@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    JdbcTemplate template;
    @Autowired
    private SessionFactory sessionFactory;

//    public CompanyServiceImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
    @Override
    public List<Company> listCompanies() {
//        String query = "select * from company";
        return sessionFactory.getCurrentSession().createQuery("from Company").list();
//        return template.query(query, new RowMapper<Company>() {
//            @Override
//            public Company mapRow(ResultSet rs, int i) throws SQLException {
//                Company company = new Company(rs.getLong("id"), 
//                        rs.getString("name"), 
//                        rs.getDate("created_at"), 
//                        rs.getDate("updated_at"), 
//                        rs.getByte("isDeleted"));
//                return company;
//            }
//        });
    }

    @Override
    public Company findById(long id) {
        Company company = sessionFactory.getCurrentSession().get(Company.class, id);
        return company != null ? company: null;
    }

    @Override
    @Transactional
    public void save(Company company) {
       company.setCreatedAt(new Date());
        sessionFactory.getCurrentSession().save(company);
//        final String name = company.getName();
//        KeyHolder key = new GeneratedKeyHolder();
//        template.update(new PreparedStatementCreator() {
//
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                String query = "INSERT INTO company (name,CREATED_AT,ISDELETED) VALUES (?,sysdate,0)";
//                final PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
//                ps.setString(1, name);
//                return ps;
//            }
//        }, key);
//
//        company.setId(key.getKey().longValue());
    }

    @Override
    @Transactional
    public void update(long id, Company company) {
        
        Company companyInDb = sessionFactory.getCurrentSession().find(Company.class, id);
        companyInDb.setName(company.getName());
        companyInDb.setUpdatedAt(new Date());  
        sessionFactory.getCurrentSession().update(companyInDb);
        
    }

    @Override
    @Transactional
    public void delete(long id) {
        Session session =  sessionFactory.getCurrentSession();
        Company companyInDb = session.get(Company.class, id);
        session.delete(companyInDb);
        session.flush();
    }

}
