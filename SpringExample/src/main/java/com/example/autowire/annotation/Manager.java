package com.example.autowire.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class Manager {

    @Autowired  /*setter injection*/
    @Qualifier("employee")/*used when we have beans with the same name*/
    private Employee employee;

    @Autowired  /*constructor injection*/
    public Manager(Employee employee){
        this.employee = employee;
    }

    @Override
    public String toString(){
        return "Manager{" +
                "employee=" + employee +
                '}';
    }

}
