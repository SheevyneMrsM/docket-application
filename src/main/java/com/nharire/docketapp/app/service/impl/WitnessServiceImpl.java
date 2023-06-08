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
      Optional <Witness> witness = witnessRepo.findById(witnessDTO.getNationalId());
      Witness witness1;
      if (witness.isPresent()){
          witness1 = witness.get();
          BeanUtils.copyProperties(witnessDTO,witness1);
      }else{
          throw new RuntimeException("No derails found, cant update!!");
      }
        BeanUtils.copyProperties(witness1,witnessDTO);
        return witnessDTO;
    }

    @Override
    public void deleteWitnessById(String id) {
        witnessRepo.deleteById(id);

    }


    @Override
    public List<Witness> getAllWitnessDetails() {
        return witnessRepo.findAll();
    }

    @Override
    public Optional<Witness> getWitness(String nationalId) {

        return witnessRepo.findById(nationalId);
    }
}
