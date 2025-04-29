package com.cart.app.service;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReplicatorJobRequestHandler implements JobRequestHandler<ReplicatorJobRequest> {

    @Autowired
    private OrderItemReplicator orderItemReplicator;

    @Override
    @Job(name = "Order Item Replicator Job",retries = 2)
    public void run(ReplicatorJobRequest myJobRequest) throws Exception {
        orderItemReplicator.process("RequestId:"+myJobRequest.getId());
    }
}
