package com.example.zadanie18.controller;

import com.example.zadanie18.component.User;
import com.example.zadanie18.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    UserRepository ur;

    @Autowired
    public UserController(UserRepository ur) {
        this.ur = ur;
    }

    @GetMapping("/printUsers")
    @ResponseBody
    public String printUsers() {
        List<User> users = ur.getUsers();
        String list = "";
        for (User user : users) {
            list += user.toString();
            list += "<br>";
        }
        return list;
    }

    @GetMapping("/addUser")
    public String addUser(@RequestParam(value = "firstName", required = false, defaultValue = "Seba") String name,
                          @RequestParam(value = "lastName", required = false, defaultValue = "Polak") String surname,
                          @RequestParam Integer age) {
        List<User> users = ur.getUsers();

        users.add(new User(name, surname, age));
        System.out.println("Dodano użytkownika poprawnie");
        return "redirect:/success.html";
    }

    @GetMapping("/removeUser")
    public String removeUser(@RequestParam(value = "firstNameDelete", required = false) String name,
                         @RequestParam(value = "lastNameDelete", required = false) String surname,
                         @RequestParam Integer ageDelete) {
        List<User> users = ur.getUsers();
        User user = new User(name, surname,ageDelete);
        if (users.remove(user)) {
            System.out.println("Usunięto użytkownika poprawnie");
            return "redirect:/successDelete.html";
        } else {
            System.out.println("Nie udało się usunąć użytkownika");
            return "redirect:/errorDelete.html";
        }
    }
}