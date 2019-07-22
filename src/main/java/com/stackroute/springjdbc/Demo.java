package com.stackroute.springjdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
          //Application context
          ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
          ContactDao dao = context.getBean("contactdao", ContactDao.class);
          //For creating table
          dao.createTable();
          //For inserting records
          dao.insetRecord(new Contact("Suchita","9988766543","IBM Banagalore"));
          dao.insetRecord(new Contact("Ananya","9188775543","IBM Chennai"));
          dao.insetRecord(new Contact("Rashi","9983455444","IBM Noida"));
          dao.insetRecord(new Contact("Mehek","8949766543","IBM Kolkata"));
          dao.insetRecord(new Contact("Naina","7988732243","IBM Hyderabad"));
          //For deleting any record
          dao.deleteRecord("Ananya");
          //To read the records from the table
          dao.readRecord();
          System.out.println(dao.readRecord().size());
          List<Contact> list=dao.readRecord();

          for(Contact record:list)
              System.out.println(record);

    }
}
