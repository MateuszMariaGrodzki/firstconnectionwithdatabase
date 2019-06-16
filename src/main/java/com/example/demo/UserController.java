package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/")
    public String getForm(){
        return "form";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute User user){
        /*
        @ModelAttribute automagicznie zamienia wyslane parametry na obiekt uzytkownika
        tzn:
        user = new User();
        user.setemail = email;
        user.setname = name;
         */
        userRepository.save(user);
        return "redirect:/users"; // przechodzi do podanego adrasu url i czysci wszystkie wyslane dane
    }

    @GetMapping("/users")
    public String getAll(ModelMap modelMap) {
        //findAll znajduje wszystkich uzytkownikow
        // modelMap sluzy do przekazywania zmiennych z Javy do thymeleafa
        modelMap.put("users" , userRepository.findAll());
        return "index";
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable Integer id, ModelMap modelMap) {
        modelMap.put("user" , userRepository.findById(id).get());
        return "show";
    }

}
