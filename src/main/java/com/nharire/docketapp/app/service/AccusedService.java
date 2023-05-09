package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.repository.AccusedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccusedService {

    @Autowired
    private final AccusedRepo accusedRepo;


    public AccusedService(AccusedRepo accusedRepo) {
        this.accusedRepo = accusedRepo;
    }
}
