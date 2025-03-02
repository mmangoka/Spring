package com.IOC.coupling;

public class NewDatabaseProvider implements UserDataProvider {
    @Override
    public String getUserDetails() {
        return "New Database provider.";
    }
}
