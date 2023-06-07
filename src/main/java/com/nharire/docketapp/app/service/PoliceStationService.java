package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.PoliceStation;
import com.nharire.docketapp.app.model.dto.PoliceStationDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PoliceStationService {

    PoliceStation savePoliceStationDetails(PoliceStationDTO policeStationDTO);

    PoliceStationDTO updatePoliceStationDetails(PoliceStationDTO policeStationDTO);

    void deletePoliceStationDetailsById(Long id);

    void deleteOfficer(Long id);

    void deleteReview(Long id);

    PoliceStationDTO addAddress(Address address);

     List<PoliceStationDTO> addReview(PoliceStation policeStation);

    Optional<PoliceStation> getPoliceStation(Long id);

    List<PoliceStation> getAllPoliceStations();

}
