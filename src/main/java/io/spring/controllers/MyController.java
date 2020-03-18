package io.spring.controllers;


import io.spring.dao.MyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    private static final Logger LOG = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private MyDao myDao;


    @GetMapping("/")
    public String getMyName() throws InterruptedException {
        System.out.println(myDao.printSomething());

        return "hello52";
    }


}
