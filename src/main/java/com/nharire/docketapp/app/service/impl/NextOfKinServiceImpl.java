package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.NextOfKin;
import com.nharire.docketapp.app.model.dto.NextOfKinDTO;
import com.nharire.docketapp.app.model.dto.response.NextOfKinResponse;
import com.nharire.docketapp.app.repository.AccusedRepo;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.repository.ComplainantRepo;
import com.nharire.docketapp.app.repository.NextOfKinRepo;
import com.nharire.docketapp.app.service.NextOfKinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class NextOfKinServiceImpl implements NextOfKinService {
    private final NextOfKinRepo nextOfKinRepo;
    private final AccusedRepo accusedRepo;

    private final AddressRepo addressRepo;

    private final ComplainantRepo complainantRepo;

    @Override
    public NextOfKinResponse saveNextOfKinDetails(NextOfKinDTO nextOfKinDTO) {
        NextOfKinResponse nextOfKinResponse = new NextOfKinResponse();
        try {
            log.info("SAVE NEXT OF KIN DETAILS: {}", nextOfKinDTO.toString());
            //create new address object
            Address address = new Address();
            if (nextOfKinDTO != null) {
                if (nextOfKinDTO.getAddress() != null) {
                    //get address details from dto
                    BeanUtils.copyProperties(nextOfKinDTO.getAddress(), address);
                    //save address to db
                    address = addressRepo.saveAndFlush(address);

                } else {
                    nextOfKinResponse.setResponseCode(400);
                    nextOfKinResponse.setDescription("No Address Details Found!!!");
                    nextOfKinResponse.setMessage("Please kindly add Address details");
                    nextOfKinResponse.setCode("DM-ADD-001");
                    return nextOfKinResponse;
                }


                //create Accused  object
            Address address2= new Address();
       if (nextOfKinDTO!= null) {
           if (nextOfKinDTO.getAccused() != null) {
               if (nextOfKinDTO.getAccused().getAddress() != null) {
                   //get address details from dto
                   BeanUtils.copyProperties(nextOfKinDTO.getAccused().getAddress(), address2);
                   address2 = addressRepo.saveAndFlush(address2);
               } else {
                   nextOfKinResponse.setResponseCode(400);
                   nextOfKinResponse.setDescription("No Address Details Found!!!");
                   nextOfKinResponse.setMessage("Please kindly add Address details");
                   nextOfKinResponse.setCode("DM-ADD-001");
                   return nextOfKinResponse;
               }
           } else {
               nextOfKinResponse.setResponseCode(400);
               nextOfKinResponse.setDescription("Request Failed on Accused Details Missing ");
               nextOfKinResponse.setMessage(" Please kindly include Accused details");
               nextOfKinResponse.setCode("DM-ACC-001");
               return nextOfKinResponse;

           }
           Accused accused = new Accused();
           accused.setAddress(address2);

           if (nextOfKinDTO != null) {
               if (nextOfKinDTO.getAccused() != null) {
                   BeanUtils.copyProperties(nextOfKinDTO.getAccused(), accused);
                   accused = accusedRepo.saveAndFlush(accused);
               }
           }


//           List<Complainant> complainantList = complainantRepo.findByNationalIdEquals(nextOfKinDTO.getNationalId());
//           Address address3 = new Address();

//           for (Complainant complainant : complainantList) {
//               if (complainantList.isEmpty()){
//
//
//               }else{
//                   complainantList.get(0);
//               }
//               if (complainant != null) {
//                   complainantList.add(complainant);
//                   //if (complainant.getNextOfKin() != null) {
//                       if (complainant.getAddress() != null) {
//                           BeanUtils.copyProperties(complainant.getAddress(), address3);
//                           address3 = addressRepo.saveAndFlush(address3);
//
//                       }

               //}

           //}

           NextOfKin nextOfKin = new NextOfKin();
           nextOfKin.setAccused(accused);
           nextOfKin.setAddress(address);
           BeanUtils.copyProperties(nextOfKinDTO, nextOfKin);

           //nextOfKin.setAddress(address3);
           log.info("Saving accused details: {}", nextOfKin);
         try {


           nextOfKin = nextOfKinRepo.saveAndFlush(nextOfKin);



         } catch (Exception ex) {
                nextOfKinResponse.setDescription("FAILED TO SAVE NEXT OF KIN");
                nextOfKinResponse.setResponseCode(500);
                nextOfKinResponse.setMessage("failed to next of kin");
                nextOfKinResponse.setCode("DM-NOK-001");
            }
           BeanUtils.copyProperties(nextOfKin, nextOfKinResponse);
           nextOfKinResponse.setResponseCode(200);
           nextOfKinResponse.setMessage("SUCCESS");
       }}
        }catch (Exception exception){
            log.info("FAILED TO SAVE NEXT OF KIN, DATABASE ERROR " + exception);
            nextOfKinResponse.setResponseCode(400);
            nextOfKinResponse.setMessage("Failed to Save Information to Database");
            nextOfKinResponse.setCode("DM-DB-001");
            nextOfKinResponse.setDescription(exception.getMessage());
        }

        return nextOfKinResponse;
    }


    @Override
    public NextOfKinResponse updateNextOfKinDetails(NextOfKinDTO nextOfKinDTO) {
        NextOfKinResponse nextOfKinResponse = new NextOfKinResponse();
        try {
            log.info("Updating Address into next of kin ");
            Optional<NextOfKin> nextOfKin = nextOfKinRepo.findByNationalIdEqualsIgnoreCase(nextOfKinDTO.getNationalId());

            NextOfKin nextOfKin1 = new NextOfKin();
            if (nextOfKin.isPresent()) {
                nextOfKin1 = nextOfKin.get();
                BeanUtils.copyProperties(nextOfKin1,nextOfKinDTO);
           }

            Optional<Address> address = addressRepo.findByIdEquals(nextOfKinDTO.getAddress().getId());

            if (address.isPresent()) {
                Address address1 = address.get();
                nextOfKin1.setAddress(address1);
                try {
                    nextOfKin1 = nextOfKinRepo.saveAndFlush(nextOfKin1);
                } catch (Exception exception) {
                    nextOfKinResponse.setMessage("failed to save address database issues");
                }
                BeanUtils.copyProperties(nextOfKin1, nextOfKinResponse);
                nextOfKinResponse.setResponseCode(200);
                nextOfKinResponse.setMessage("SUCCESS");
            }
        }catch (Exception e){
            log.info("FAILED TO SAVE NEXT OF KIN" + e);
            nextOfKinResponse.setResponseCode(500);
            nextOfKinResponse.setMessage("failed to save next of kin information to database ");
            nextOfKinResponse.setCode("DM-DB-001");
            nextOfKinResponse.setDescription(e.getMessage());
        }


        return nextOfKinResponse;
    }

    @Override
    public NextOfKin deleteNextOfKinById(String nationalId) {
        nextOfKinRepo.deleteById(nationalId);

        return null;
    }

    @Override
    public void deleteAddressDetails(Long id) {
        nextOfKinRepo.deleteById(String.valueOf(id));

    }

    @Override
    public List<NextOfKin> getAllNextOfKinDetails() {

        return nextOfKinRepo.findAll();
    }

    @Override
    public Optional<NextOfKin> getNextOfKinDetails(String nationalId) {

        return nextOfKinRepo.findById(nationalId);
    }



    @Override
    public NextOfKin addAddressDetails(Address address) {

        return nextOfKinRepo.addAddressDetails(address);
    }
}
