package com.example.zadanie18.repository;

import com.example.zadanie18.component.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public UserRepository() {
        users.add(new User("Jan", "Kowal", 87));
        users.add(new User("Janina", "Kowalska", 78));
        users.add(new User("Janek", "Kowalek", 13));
    }

    public boolean lookForUser(String name, String surname, int age) {
        List<User> users = getUsers();
        User user = new User(name, surname, age);
        for (int i = 0; i < users.size(); i++) {
            User user1 = users.get(i);
            if (user.equals(user1))
                users.remove(user1);
            return true;
        }
        return false;
    }
}