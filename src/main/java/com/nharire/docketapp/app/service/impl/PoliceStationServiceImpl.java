package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Officer;
import com.nharire.docketapp.app.model.PoliceStation;
import com.nharire.docketapp.app.model.dto.OfficerDTO;
import com.nharire.docketapp.app.model.dto.PoliceStationDTO;
import com.nharire.docketapp.app.model.dto.response.PoliceStationResponse;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.repository.OfficerRepo;
import com.nharire.docketapp.app.repository.PoliceStationRepo;
import com.nharire.docketapp.app.service.PoliceStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class PoliceStationServiceImpl implements PoliceStationService {

    private final PoliceStationRepo policeStationRepo;
    private final AddressRepo addressRepo;
    private final OfficerRepo officerRepo;

    @Override
    public PoliceStationResponse savePoliceStationDetails(PoliceStationDTO policeStationDTO) {

        PoliceStationResponse policeStationResponse = new PoliceStationResponse();
        try {
            log.info("SAVE POLICE STATION DETAILS: {}", policeStationDTO.toString());

            Address address = new Address();

            if (policeStationDTO != null) {
                if (policeStationDTO.getAddress() != null) {
                    BeanUtils.copyProperties(policeStationDTO.getAddress(), address);
                    //save Address details
                    address = addressRepo.save(address);
                } else {
                    //if address is null get a response message that no details are found, & they must be added
                    policeStationResponse.setResponseCode(400);
                    policeStationResponse.setDescription("No Address Details Found!!!");
                    policeStationResponse.setMessage("Please kindly add Address details");
                    policeStationResponse.setCode("DM-ADD-001");
                    //adding return because address should be saved in accused no matter what
                    return policeStationResponse;
                }
            }

//            Address address1 = new Address();
//            if (policeStationDTO != null) {
//                if (policeStationDTO.getOfficerNationalId() != null) {
//
//                        BeanUtils.copyProperties(policeStationDTO.getOfficerNationalId(), address1);
//                        address1 = addressRepo.saveAndFlush(address1);
//                    } else {
//                        policeStationResponse.setResponseCode(400);
//                        policeStationResponse.setDescription(" Please Add Address Details ");
//                        policeStationResponse.setMessage("Please kindly add Address Details");
//                        policeStationResponse.setCode("DM-ADD-001");
//                        return policeStationResponse;
//                    }
//                } else {
//                    policeStationResponse.setResponseCode(400);
//                    policeStationResponse.setDescription("Request Failed on Officer Details Missing ");
//                    policeStationResponse.setMessage(" Please kindly include Officer details");
//                    policeStationResponse.setCode("DM-OFF-001");
//                    return policeStationResponse;
//                }


                //create officer in charge obj
                Officer officer = new Officer();

               // officer.setAddress(address1);
                if (policeStationDTO!= null){
                    if (policeStationDTO.getOfficerNationalId()!= null){
                        BeanUtils.copyProperties(policeStationDTO.getOfficerNationalId(),officer);
                        officer = officerRepo.saveAndFlush(officer);
                    }
                }
                PoliceStation policeStation = new PoliceStation();

                policeStation.setOfficerNationalId(String.valueOf(officer));
                policeStation.setAddress(address);
                BeanUtils.copyProperties(policeStationDTO, policeStation);

                log.info("Saving police station details: {}", policeStation);
           try {
               policeStationRepo.saveAndFlush(policeStation);
           }
           catch (Exception ex) {
                   policeStationResponse.setDescription("FAILED TO SAVE POLICE STATION");
                   policeStationResponse.setResponseCode(500);
                   policeStationResponse.setMessage("failed to Police station details");
                   policeStationResponse.setCode("DM-POLICE-001");
               }
                BeanUtils.copyProperties(policeStation,policeStationResponse);
                policeStationResponse.setResponseCode(200);
                policeStationResponse.setMessage("SUCCESS");
           }
         catch (Exception exception){
                log.info("FAILED TO SAVE NEXT OF KIN, DATABASE ERROR " + exception);
                policeStationResponse.setResponseCode(400);
                policeStationResponse.setMessage("Failed to Save Information to Database");
                policeStationResponse.setCode("DM-DB-001");
                policeStationResponse.setDescription(exception.getMessage());
            }

        return policeStationResponse;
    }


    @Override
    public PoliceStationResponse updatePoliceStationDetails(PoliceStationDTO policeStationDTO) {
        PoliceStationResponse policeStationResponse = new PoliceStationResponse();
try{
        log.info("UPDATING POLICE STATION DETAILS : {}", policeStationDTO.toString());

        Optional<PoliceStation> policeStation = policeStationRepo.findByIdEquals(policeStationDTO.getId()   );
        PoliceStation policeStation1 = new PoliceStation();

        if (policeStation.isPresent()) {
            policeStation1 = policeStation.get();
            BeanUtils.copyProperties(policeStationDTO, policeStation1);
        }
        Optional<Officer> officer = officerRepo.findByNationalIdEqualsIgnoreCase(policeStationDTO.getOfficerNationalId());
        if (officer.isPresent()){
            Officer officer1 =officer.get();
            if (officer1.getPoliceStationId()!= null){
                BeanUtils.copyProperties(officer1.getPoliceStationId(),officer1);
            }
            officer1.setPoliceStationId(String.valueOf(policeStation1));

        try {
            officer1 = officerRepo.saveAndFlush(officer1);
        }catch (Exception exception){
            policeStationResponse.setMessage("failed to save officer database issues!!!");
          }
            policeStationResponse.setOfficerInCharge(officer1);
            //copy properties from accused to response
            BeanUtils.copyProperties(officer1, policeStationResponse);
            policeStationResponse.setMessage("SUCCESS");
            policeStationResponse.setResponseCode(200);
            return policeStationResponse;

        }
        else {
            policeStationResponse.setResponseCode(400);
            policeStationResponse.setMessage("No police stations with this Id is registered");
            policeStationResponse.setCode("DB-POL-002");
            policeStationResponse.setDescription("Failed to update police station to crime register");
            BeanUtils.copyProperties(policeStation1, policeStationResponse);
            return policeStationResponse;
        }
       }catch (Exception exception){
       log.info("FAILED TO SAVE COMPLAINANT, DATABASE ERROR " + exception);
         policeStationResponse.setResponseCode(400);
        policeStationResponse.setMessage("Failed to Save Information to Database");
        policeStationResponse.setCode("DM-DB-001");
        policeStationResponse.setDescription(exception.getMessage());
}


        return policeStationResponse;
    }

    @Override
    public void deletePoliceStationDetailsById(Long id) {
        policeStationRepo.deleteById(id);

    }

    @Override
    public void deleteOfficer(Long id) {
        policeStationRepo.deleteById(id);

    }

    @Override
    public void deleteReview(Long id) {
        policeStationRepo.deleteById(id);

    }
    @Override
    public PoliceStation addAddress(Address address) {
        PoliceStation policeStation = policeStationRepo.getById(address.getId());
        policeStation.getAddress().getId();
        return policeStationRepo.save(policeStation);
    }

    @Override
    public List<PoliceStationDTO> addReview(PoliceStation policeStation) {
        return
                policeStationRepo.addReview(policeStation);
    }

    @Override
    public Optional<PoliceStation> getPoliceStation(Long id) {

        return policeStationRepo.findById(id);
    }

    @Override
    public List<PoliceStation> getAllPoliceStations() {
        return policeStationRepo.findAll();
    }
}
