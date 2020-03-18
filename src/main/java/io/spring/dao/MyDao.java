package io.spring.dao;

import io.spring.aop.TrackTime;
import org.springframework.stereotype.Repository;

@Repository
public class MyDao {

    @TrackTime
    public String printSomething() throws InterruptedException {
        Thread.sleep((long) ((3000 - 1500) + 1 - 1500));
        return "dummy string";
    }
}
