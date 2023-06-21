package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.NextOfKinDTO;
import com.nharire.docketapp.app.model.dto.response.NextOfKinResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NextOfKinService {

    NextOfKinResponse saveNextOfKinDetails(NextOfKinDTO nextOfKinDTO);

    NextOfKinDTO updateNextOfKinDetails(NextOfKinDTO nextOfKinDTO);

    void deleteNextOfKinById(String nationalId);

    void deleteAddressDetails(Long id );

    List<NextOfKin> getAllNextOfKinDetails();

    Optional<NextOfKin> getNextOfKinDetails(String nationalId);

    NextOfKin addAddressDetails(Address address);




}
