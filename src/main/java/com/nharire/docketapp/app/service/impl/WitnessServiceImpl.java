package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Witness;
import com.nharire.docketapp.app.model.dto.WitnessDTO;
import com.nharire.docketapp.app.model.dto.response.WitnessResponse;
import com.nharire.docketapp.app.repository.AddressRepo;
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
    private final AddressRepo addressRepo;

    @Override
    public WitnessResponse saveWitnessDetails(WitnessDTO witnessDTO) {
        WitnessResponse witnessResponse = new WitnessResponse();
        try {
            log.info("SAVE WITNESS DETAILS: {}", witnessDTO.toString());
            Address address = new Address();
            if (witnessDTO != null) {
                if (witnessDTO.getAddress() != null) {
                    BeanUtils.copyProperties(witnessDTO.getAddress(), address);
                } else {
                    witnessResponse.setResponseCode(400);
                    witnessResponse.setDescription(" Please Add Address Details ");
                    witnessResponse.setMessage("Please kindly add Address Details");
                    witnessResponse.setCode("DM-ADD-001");
                    return witnessResponse;
                }
            }
            Address address1 = addressRepo.saveAndFlush(address);
            Witness witness = new Witness();
            witness.setAddress(address1);
            BeanUtils.copyProperties(witnessDTO, witness);
            log.info("Saving witness details: {}", witness);
            try {
                witness = witnessRepo.saveAndFlush(witness);
            } catch (Exception exception) {
                witnessResponse.setDescription("FAILED TO SAVE USER DETAILS");
                witnessResponse.setResponseCode(500);
                witnessResponse.setMessage("failed to save user details");
            }
            BeanUtils.copyProperties(witness, witnessResponse);
            witnessResponse.setMessage("SUCCESS");
            witnessResponse.setResponseCode(200);
        }catch (Exception e){
            log.info("FAILED TO SAVE WITNESS IN THE DB" + e);
            witnessResponse.setDescription("FAILED TO SAVE WITNESS IN THE DB");
            witnessResponse.setResponseCode(400);
            witnessResponse.setCode("DM-DB-001");
            witnessResponse.setMessage(e.getMessage());
        }
        return witnessResponse;
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
