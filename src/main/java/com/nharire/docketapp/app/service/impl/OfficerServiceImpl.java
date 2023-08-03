package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Officer;
import com.nharire.docketapp.app.model.dto.OfficerDTO;
import com.nharire.docketapp.app.model.dto.response.OfficerResponse;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.repository.OfficerRepo;
import com.nharire.docketapp.app.service.OfficerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class OfficerServiceImpl implements OfficerService {

    private final OfficerRepo officerRepo;
    private final AddressRepo addressRepo;

    @Override
    public OfficerResponse saveOfficerDetails(OfficerDTO officerDTO) {
        
        OfficerResponse officerResponse = new OfficerResponse();
        try {
            log.info("SAVE OFFICER DETAILS: {}", officerDTO.toString());
            Address address = new Address();
            if (officerDTO != null) {
                if (officerDTO.getAddress() != null) {
                    BeanUtils.copyProperties(officerDTO.getAddress(), address);
                    address = addressRepo.saveAndFlush(address);
                } else {
                    officerResponse.setResponseCode(400);
                    officerResponse.setDescription("No Address Details Found!!!");
                    officerResponse.setMessage("Please kindly add Address details");
                    officerResponse.setCode("DM-ADD-001");
                    return officerResponse;
                }

            }

            Officer officer = new Officer();
            officer.setAddress(address);
            BeanUtils.copyProperties(officerDTO, officer);
            log.info("Saving officer details: {}", officer);
            try {
                officer = officerRepo.saveAndFlush(officer);
                BeanUtils.copyProperties(officer, officerResponse);
                officerResponse.setResponseCode(200);
                officerResponse.setMessage("SUCCESS");
                return officerResponse;
            } catch (Exception ex) {
                officerResponse.setResponseCode(500);
                officerResponse.setDescription("FAILED TO SAVE OFFICER");
                officerResponse.setMessage("failed to save officer");
                officerResponse.setCode("DM-OFF-001");

            }
        }catch (Exception exception){
            log.info("FAILED TO SAVE OFFICER, DATABASE ERROR " + exception);
            officerResponse.setResponseCode(400);
            officerResponse.setMessage("Failed to Save Information to Database");
            officerResponse.setCode("DM-DB-001");
            officerResponse.setDescription(exception.getMessage());
        }
        return officerResponse;
    }

    @Override
    public OfficerResponse updateOfficerDetails(OfficerDTO officerDTO) {
        OfficerResponse officerResponse = new OfficerResponse();
        try {
            log.info("UPDATING OFFICER DETAILS:{}", officerDTO.toString());

            Optional<Officer> officer = officerRepo.findByNationalIdEqualsIgnoreCase(officerDTO.getNationalId());
            Officer officer1 = new Officer();
            if (officer.isPresent()) {
                officer1 = officer.get();
                BeanUtils.copyProperties(officerDTO, officer1);
            }
            List<Address> address = addressRepo.findByStreetAddressEqualsIgnoreCase(officerDTO.getAddress().getStreetAddress());


            if (address != null) {
                if (!address.isEmpty()) {
                    Address address1 = address.get(0);
                    officer1.setAddress(address1);
                    try {
                        officer1 = officerRepo.saveAndFlush(officer1);
                    } catch (Exception exception) {
                        officerResponse.setMessage("failed to save address database issues");
                    }
                    BeanUtils.copyProperties(officer1, officerResponse);
                    officerResponse.setResponseCode(200);
                    officerResponse.setMessage("SUCCESS");
                }
            }
        }catch (Exception exception){
            log.info("FAILED TO SAVE OFFICER" + exception);
            officerResponse.setResponseCode(500);
            officerResponse.setMessage("failed to officer information to database ");
            officerResponse.setCode("DM-DB-001");
            officerResponse.setDescription(exception.getMessage());

        }
        return officerResponse;
    }

    @Override
    public Officer deleteOfficerById(String nationalId) {
        officerRepo.deleteById(nationalId);

        return null;
    }

    @Override
    public List<Officer> getAllOfficers() {

        return officerRepo.findAll();
    }

    @Override
    public Optional<Officer> getOfficerDetails(String nationalId) {

        return officerRepo.findById(nationalId);
    }

}
