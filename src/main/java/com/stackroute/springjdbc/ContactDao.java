package com.stackroute.springjdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContactDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    //getters and setters
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource=dataSource;
    }

    public JdbcTemplate getJdbcTemplate()
    {

        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    //method for creating the table in the database
    public void createTable()
    {
        String sql = "CREATE TABLE Contact(Name VARCHAR(50),Phone VARCHAR(10),Address VARCHAR(50))";
        jdbcTemplate.execute(sql);
    }

    //method for inserting records in the table
    public void insetRecord(Contact contact)
    {
        String sql="INSERT INTO Contact (Name,Phone,Address) VALUES(?,?,?)";
        jdbcTemplate.update(sql,new Object[]{contact.getName(),contact.getPhone(),contact.getAddress()});

    }

    //method for deleting the record from the table
    public void deleteRecord(String name)
    {
        String sql="DELETE FROM Contact where name=?";
        jdbcTemplate.update(sql,name);

    }

    //method for reading the data from the table
    public List<Contact> readRecord()
    {
        String sql="SELECT * FROM Contact";
        return jdbcTemplate.query(sql,new ContactMapper());

    }

    private static final class ContactMapper implements RowMapper<Contact>
    {

        @Override
        public Contact mapRow(ResultSet resultSet, int rownum) throws SQLException {
            Contact contact=new Contact();
            contact.setName(resultSet.getString("Name"));
            contact.setPhone(resultSet.getString("Phone"));
            contact.setAddress(resultSet.getString("Address"));
            return contact;
        }
    }
}
