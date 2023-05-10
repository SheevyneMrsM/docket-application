package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.ComplainantDTO;
import com.nharire.docketapp.app.service.ComplainantService;

import java.util.List;
import java.util.Optional;

public class ComplainantServiceImpl implements ComplainantService {
    @Override
    public ComplainantDTO saveComplainantDetails(ComplainantDTO complainantDTO) {
        return null;
    }

    @Override
    public ComplainantDTO updateComplainantDetails(ComplainantDTO complainantDTO) {
        return null;
    }

    @Override
    public ComplainantDTO addNextOfKin(NextOfKin nextOfKin) {
        return null;
    }

    @Override
    public void deleteComplainantById(String nationalId) {

    }

    @Override
    public Optional<ComplainantDTO> getComplainantDetails(String nationalId) {
        return Optional.empty();
    }

    @Override
    public List<ComplainantDTO> getAllComplainantDetails() {
        return null;
    }

    @Override
    public List<ComplainantDTO> getDateOfComplaint(String dateReported) {
        return null;
    }
}
