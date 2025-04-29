package com.enque.app.controller;

import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

    @Autowired
    private JobScheduler jobScheduler;

    @GetMapping("/hello")
    public ResponseEntity sayHello(){
        var message = Map.of("message","Hello World !!");
        return ResponseEntity.ok(message);
    }

    @PostMapping("/job")
    public void createJob() {
        IntStream.range(1,11).parallel().forEach(value -> {
            jobScheduler.enqueue(() -> System.out.println("#"+value+": Up & Running from a background Job"));
        });
    }
}
