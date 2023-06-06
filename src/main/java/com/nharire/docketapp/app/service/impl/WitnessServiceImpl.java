package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Witness;
import com.nharire.docketapp.app.model.dto.WitnessDTO;
import com.nharire.docketapp.app.repository.WitnessRepo;
import com.nharire.docketapp.app.service.WitnessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class WitnessServiceImpl implements WitnessService {

    private final WitnessRepo witnessRepo;

    @Override
    public Witness saveWitnessDetails(WitnessDTO witnessDTO) {
        log.info("SAVE WITNESS DETAILS: {}", witnessDTO.toString());
        Witness witness = new Witness();
        BeanUtils.copyProperties(witnessDTO,witness);
        log.info("Saving witness details: {}", witness);
        return witnessRepo.save(witness);
    }

    @Override
    public WitnessDTO updateWitnessDetails(WitnessDTO witnessDTO) {
        return null;
    }

    @Override
    public void deleteWitnessById(String id) {

    }

    @Override
    public WitnessDTO addWitness(Witness witness) {
        return null;
    }

    @Override
    public List<Witness> getAllWitnessDetails() {
        return null;
    }

    @Override
    public Optional<Witness> getWitness(String nationalId) {
        return Optional.empty();
    }
}
