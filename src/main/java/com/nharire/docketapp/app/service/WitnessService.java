package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Witness;
import com.nharire.docketapp.app.model.dto.WitnessDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface WitnessService {

    Witness saveWitnessDetails(WitnessDTO witnessDTO);

    WitnessDTO updateWitnessDetails(WitnessDTO witnessDTO);

    void deleteWitnessById(String id);

    WitnessDTO addWitness(Witness witness);

    List<WitnessDTO> getAllWitnessDetails();

    Optional<WitnessDTO> getWitness(String nationalId);
}
