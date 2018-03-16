/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.resolve.api.serviceImpl;

import com.resolve.api.model.Domain;
import com.resolve.api.service.DomainService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author furqan.ahmad
 */
@Service
public class DomainServiceImpl implements DomainService {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Domain> getDomains() {
        String query = "select * from domain";
        List<Domain> dList = template.query(query, new RowMapper<Domain>() {
            @Override
            public Domain mapRow(ResultSet rs, int i) throws SQLException {
                Domain domain = new Domain(rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getDate("created_at"),
                        rs.getDate("updated_at"),
                        rs.getByte("isdeleted")
                );
                return domain;
            }
        });
       
        return dList;
    }

    @Override
    public Domain findById(long id) {
        String query = "select * from domain where id = ?";
        return template.query(query, new ResultSetExtractor<Domain>() {
            @Override
            public Domain extractData(ResultSet rs) throws SQLException, DataAccessException {
                Domain domain = null;
                if (rs.next()) {
                    domain = new Domain(rs.getLong("ID"), rs.getString("NAME"));
                }
                return domain;
            }
        }, id);
    }

    @Override
    public void save(Domain domain) {
        final String name = domain.getName();
        KeyHolder key = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String query = "INSERT INTO DOMAIN (name,CREATED_AT,ISDELETED) VALUES (?,sysdate,0)";
                final PreparedStatement ps = connection.prepareStatement(query, new String[]{"id"});
                ps.setString(1, name);
                return ps;
            }
        }, key);

        domain.setId(key.getKey().longValue());
    }

    @Override
    public void update(long id, Domain domain) {
        String query = "update domain set name = ?, UPDATED_AT=sysdate where id = ?";
        template.update(query, domain.getName(), id);
    }

    @Override
    public void delete(long id) {

    }

}
