package com.loose.coupling;

public class UserDatabaseProvider  implements UserDataProvider{
  @Override
    public String getUserDetails(){
        return "User details from database";
    }
}
