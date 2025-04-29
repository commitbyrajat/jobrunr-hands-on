package com.enque.app.controller;

import com.cart.app.service.OrderItemReplicator;
import com.cart.app.service.ReplicatorJobRequest;
import org.jobrunr.jobs.JobId;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.BackgroundJobRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1/replicator")
public class ReplicatorController {
    @PostMapping("/send/batch")
    public void send(){
        IntStream.range(1,11).parallel().forEach(value -> {
            BackgroundJob.<OrderItemReplicator>enqueue(x -> x.process("RequestId:"+value));
        });
    }

    @PostMapping("/send")
    public JobId sendJobRequest(){
        JobId id = BackgroundJobRequest.enqueue(new ReplicatorJobRequest(UUID.randomUUID()));
        return id;
    }
}
