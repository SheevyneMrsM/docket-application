package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Witness;
import com.nharire.docketapp.app.model.dto.WitnessDTO;
import com.nharire.docketapp.app.model.dto.response.WitnessResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WitnessService {

    WitnessResponse saveWitnessDetails(WitnessDTO witnessDTO);

    WitnessResponse updateWitnessDetails(WitnessDTO witnessDTO);

    Witness deleteWitnessById(String id);
    List<Witness> getAllWitnessDetails();

    Optional<Witness> getWitness(String nationalId);
}
