package com.tight.coupling;

public class TightCouplingExample {

    public static void main(String[] args) {
        UserManager userManger = new UserManager();
        System.out.println(userManger.getUserInfo());
    }
}
