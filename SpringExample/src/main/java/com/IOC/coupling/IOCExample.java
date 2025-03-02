package com.IOC.coupling;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCExample {

    public static void main(String[] args) {

        ApplicationContext  context =
                new ClassPathXmlApplicationContext("applicationIOCLooseCouplingExample.xml");

        UserManager userManger = (UserManager) context.getBean("userManagerWithUserDataProvider");
        System.out.println(userManger.getUserInfo());


        UserManager userManagerWithWS = (UserManager) context.getBean("userManagerWithwebServiceDataProvider");
        System.out.println(userManagerWithWS.getUserInfo());


         UserManager newDatabaseProviderDB = (UserManager) context.getBean("userManagerWithNewDatabaseProvider");
        System.out.println(newDatabaseProviderDB.getUserInfo());
    }
}
