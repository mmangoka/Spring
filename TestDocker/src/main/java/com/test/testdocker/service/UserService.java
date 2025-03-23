package com.test.testdocker.service;


import com.test.testdocker.api.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService(){
        userList = new ArrayList<>();
        User user = new User(1,"Michelle",12,"south c");
        User user1 = new User(2,"Liner",12,"south c");
        User user2 = new User(3,"chao",12,"south c");
        User user3 = new User(4,"kikutyu",12,"south c");
        User user4 = new User(5,"Venom",12,"south c");

        userList.addAll(Arrays.asList(user, user1, user2, user3, user4));


    }

    public Optional<User> getUser(int id){
         Optional optional = Optional.empty();

         for(User user:userList) {
             if (id == user.getId()) {
                 optional = Optional.of(user);
                 return optional;
             }
         }

         return optional;
    }

}
