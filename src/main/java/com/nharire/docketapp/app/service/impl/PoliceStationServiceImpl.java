package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Officer;
import com.nharire.docketapp.app.model.PoliceStation;
import com.nharire.docketapp.app.model.dto.OfficerDTO;
import com.nharire.docketapp.app.model.dto.PoliceStationDTO;
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
    public PoliceStation savePoliceStationDetails(PoliceStationDTO policeStationDTO) {
        log.info("SAVE POLICE STATION DETAILS: {}", policeStationDTO.toString());
        try {
            PoliceStation policeStation = new PoliceStation();
            //get officer in charge details
            OfficerDTO officerDTO = policeStationDTO.getOfficerInCharge();

            //create officer in charge obj
            Officer officer = new Officer();
            Address address = new Address();
            BeanUtils.copyProperties(policeStationDTO.getAddress(), address);

            //save Address details
            addressRepo.save(address);

            officer.setAddress(address);
            BeanUtils.copyProperties(officerDTO, officer);

            //save officer in charge to db
            Officer officer1 = officerRepo.save(officer);
            policeStation.setOfficerInCharge(officer1);
            policeStation.setAddress(address);

            BeanUtils.copyProperties(policeStationDTO, policeStation);
            log.info("Saving police station details: {}", policeStation);
            return policeStationRepo.save(policeStation);

        }catch(Exception  exception){
            log.info( " Failed TO SAVE DATA "+exception.getMessage());
        }
        return  new PoliceStation();
    }

    @Override
    public PoliceStationDTO updatePoliceStationDetails(PoliceStationDTO policeStationDTO) {
        Optional<PoliceStation> policeStation = policeStationRepo.findByPoliceStationNameEqualsIgnoreCase(policeStationDTO.getPoliceStationName());
        PoliceStation policeStation1;
        if (policeStation.isPresent()){
            policeStation1 = policeStation.get();
            BeanUtils.copyProperties(policeStationDTO, policeStation1);
        }else {
            throw new RuntimeException("No details found, cant update!!!");
        }
        BeanUtils.copyProperties(policeStation1,policeStationDTO);
        return policeStationDTO;
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
