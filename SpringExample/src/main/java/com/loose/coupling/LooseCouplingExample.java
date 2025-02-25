package com.loose.coupling;

public class LooseCouplingExample {

    public static void main(String[] args) {
        UserDatabaseProvider databaseProvider =  new UserDatabaseProvider();
        UserManager userManger = new UserManager(databaseProvider);
        System.out.println(userManger.getUserInfo());


        UserDataProvider webServiceProvider =  new WebServiceDataProvider();
        UserManager userManagerWithWS =  new UserManager(webServiceProvider);
        System.out.println(userManagerWithWS.getUserInfo());


        UserDataProvider newDatabaseProvider =  new NewDatabaseProvider();
        UserManager newDatabaseProviderDB =  new UserManager(newDatabaseProvider);
        System.out.println(newDatabaseProviderDB.getUserInfo());
    }
}
