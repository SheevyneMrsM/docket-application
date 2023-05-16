package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.NextOfKinDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NextOfKinService {

    NextOfKin saveNextOfKinDetails(NextOfKinDTO nextOfKinDTO);

    NextOfKinDTO updateNextOfKinDetails(NextOfKinDTO nextOfKinDTO);

    void deleteNextOfKinById(String nationalId);

    void deleteAddressDetails(Long id );

    List<NextOfKinDTO> getAllNextOfKinDetails();

    Optional<NextOfKinDTO> getNextOfKinDetails(String nationalId);

    NextOfKinDTO addNextOfKinDetails(NextOfKin nextOfKin);

    NextOfKinDTO addAddressDetails(NextOfKin nextOfKin);




}
