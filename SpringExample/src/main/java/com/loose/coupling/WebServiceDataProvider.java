package com.loose.coupling;

public class WebServiceDataProvider implements UserDataProvider{


    @Override
    public String getUserDetails() {
        return "Fetching data from webservice.";
    }
}
