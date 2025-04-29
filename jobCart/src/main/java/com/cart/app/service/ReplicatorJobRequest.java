package com.cart.app.service;

import org.jobrunr.jobs.lambdas.JobRequest;

import java.util.UUID;

public class ReplicatorJobRequest implements JobRequest {
    private UUID id;

    public ReplicatorJobRequest(UUID id) {
        this.id = id;
    }

    @Override
    public Class<ReplicatorJobRequestHandler> getJobRequestHandler() {
        return ReplicatorJobRequestHandler.class;
    }

    public UUID getId() {
        return id;
    }
}
