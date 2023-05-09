package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.repository.AddressRepo;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepo addressRepo;

    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }
}
