package com.transaction.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbTest {

    public static void  main(String... args){

        String url ="jdbc:postgresql://localhost:5432/transaction_db";
        String user = "postgres";
        String password = "postgres";

        try(Connection conn = DriverManager.getConnection(url,user,password)){
            if(conn != null){
                System.out.println("Connection successful");
            }else{
                System.out.println("Connection failed");
            }

        }catch(SQLException e){
            System.out.println("Error" +  e.getMessage());
        }

    }
}
