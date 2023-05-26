package com.nharire.docketapp.app.controller;

import com.nharire.docketapp.app.model.*;
import com.nharire.docketapp.app.model.dto.*;
import com.nharire.docketapp.app.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Component
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class ApplicationController {

    private final AccusedService accusedService;
    private final AddressService addressService;
    private final ComplainantService complainantService;
    private final CrimeRegisterService crimeRegisterService;
    private final NextOfKinService nextOfKinService;
    private final OfficerService officerService;
    private final PoliceStationService policeStationService;
    private final ReportService reportService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final WitnessService witnessService;

    @PostMapping("/save/accused")
    public ResponseEntity<Accused> saveAccusedDetails(@RequestBody AccusedDTO accusedDTO){
        log.info("SAVING ACCUSED DETAILS: {}", accusedDTO.toString());
        Accused accused = accusedService.saveAccusedDetails(accusedDTO);
        log.info("accused details: {}", accused.toString());
        crimeRegisterService.addAccused(accused);
        return new ResponseEntity(accused, HttpStatus.OK);

    }
     @PostMapping("/save/address")
    public ResponseEntity<Address> saveAddressDetails(@RequestBody AddressDTO addressDTO){
        log.info("SAVING ADDRESS DETAILS: {}",addressDTO.toString());
        Address address = addressService.saveAddressDetails(addressDTO);
        log.info("address details : {}", address.toString());
        return  new ResponseEntity(address, HttpStatus.OK);

    }
    @PostMapping("/save/crime_register")
    public ResponseEntity<CrimeRegister> saveCrimeRegisterDetails(@RequestBody CrimeRegisterDTO crimeRegisterDTO){
        log.info("SAVE CRIME REGISTER DETAILS: {} ",crimeRegisterDTO.toString());
        CrimeRegister crimeRegister = crimeRegisterService.saveCrimeRegisterDetails(crimeRegisterDTO);
        log.info("crime register details: {} ", crimeRegister.toString());
       return new ResponseEntity(crimeRegister, HttpStatus.OK);
    }
      @PostMapping("/save/complainant")
     public ResponseEntity<Complainant> saveComplainantDetails(@RequestBody ComplainantDTO complainantDTO){
        log.info("SAVE COMPLAINANT DETAILS: {}" ,complainantDTO.toString());
        Complainant complainant = complainantService.saveComplainantDetails(complainantDTO);
        log.info("complainant details: {}", complainant.toString());
        return new ResponseEntity(complainant,HttpStatus.OK);

     }
     @PostMapping("/save/NextOfKin")
     public ResponseEntity<NextOfKin> saveNextOfKinDetails(@RequestBody @Validated NextOfKinDTO nextOfKinDTO){
        log.info("SAVE NEXT OF KIN DETAILS: {}", nextOfKinDTO.toString());
        NextOfKin nextOfKin = nextOfKinService.saveNextOfKinDetails(nextOfKinDTO);
        log.info("saving next of kin details: {}", nextOfKin.toString());
        accusedService.addNextOfKin(nextOfKin);
        return new ResponseEntity(accusedService.addNextOfKin(nextOfKin),HttpStatus.OK);
     }
     @PostMapping("/save/officer")
     public  ResponseEntity<Officer> saveOfficerDetails(@RequestBody OfficerDTO officerDTO ){
        log.info("SAVE OFFICER DETAILS: {}", officerDTO.toString());
        Officer officer = officerService.saveOfficerDetails(officerDTO);
        log.info("Saving officer details: {}", officer.toString());
        return new ResponseEntity(officer, HttpStatus.OK);
     }
     @PostMapping("/save/police station")
     public ResponseEntity<PoliceStation> savePoliceStationDetails(@RequestBody PoliceStationDTO policeStationDTO){
        log.info("SAVE POLICE STATION DETAILS: {}", policeStationDTO.toString());
        PoliceStation policeStation = policeStationService.savePoliceStationDetails(policeStationDTO);
        log.info("Saving Police station details: {}",policeStation.toString());
        return new ResponseEntity(policeStation,HttpStatus.OK);
    }
    @PostMapping("/save/report")
    public ResponseEntity<Report> saveReportDetails(@RequestBody ReportDTO reportDTO){
        log.info("SAVE REPORT DETAILS: {}", reportDTO);
        Report report = reportService.saveReportDetails(reportDTO);
        log.info("Saving report details: {}",report.toString());
        return new ResponseEntity(report, HttpStatus.OK);
    }
    @PostMapping("/save/reviews")
    public ResponseEntity<Review>  saveReviewDetails(@RequestBody ReviewDTO reviewDTO){
        log.info("SAVE REVIEWS: {}", reviewDTO.toString());
        Review review = reviewService.saveReviews(reviewDTO);
        log.info("Saving reviews: {}", review.toString());
        return new ResponseEntity(review, HttpStatus.OK);
    }
    @GetMapping("/get/accused")
    public ResponseEntity<List<Accused>>  getAllAccusedDetails(){
        List<Accused> accusedList = accusedService.getAllAccusedDetails();
        return new ResponseEntity(accusedList, HttpStatus.OK);
    }
    @GetMapping("/get/accused/{national_id}")
    public ResponseEntity<Accused> getAccusedDetails(@PathVariable String nationalId){
        log.info("SEARCHING ACCUSED USING NATIONAL ID: {}", nationalId);
        Optional<Accused> accused = accusedService.getAccusedDetails(nationalId);
        Accused accused1;
        if (accused.isPresent()){
            accused1 = accused.get();
        }else{
            throw new RuntimeException("The given national id was not found");
        }
        return new ResponseEntity(accused1, HttpStatus.OK);
    }
       @GetMapping("/get/address/{id}")
    public ResponseEntity<Address> getAddressDetails(@PathVariable Long id){
        log.info("SEARCHING ADDRESS USING ID: {}",id);
        Optional<Address> address = addressService.getAddressDetails(id);
        Address address1;
        if (address.isPresent()){
            address1 = address.get();
        }else{
            throw new RuntimeException("The given address id was not found");
        }
        return new ResponseEntity(address1, HttpStatus.OK);
    }
    @GetMapping("/get/complainant/{national_id}")
    public ResponseEntity<Complainant> getComplainantDetails(@PathVariable String nationalId){
        log.info("SEARCHING COMPLAINANT USING NATIONAL ID: {}", nationalId);
        Optional<Complainant> complainant = complainantService.getComplainantDetails(nationalId);
        Complainant complainant1;
        if (complainant.isPresent()){
            complainant1 = complainant.get();
        }else {
            throw new RuntimeException("The given national id was not found!!!");
        }
        return new ResponseEntity(complainant1,HttpStatus.OK);
    }
    @GetMapping("/get/complainant")
    public ResponseEntity<List<Complainant>> getAllComplainantDetails(){
        List<Complainant> complainantList = complainantService.getAllComplainantDetails();
        return new ResponseEntity(complainantList,HttpStatus.OK);
    }
     @GetMapping("/get/crime_register")
    public ResponseEntity<?> getAllCrimeRegisterDetails(){
        List<CrimeRegister> crimeRegisterList= crimeRegisterService.getAllCrimeRegisterDetails();
        return new ResponseEntity(crimeRegisterList,HttpStatus.OK);
    }



//    public ResponseEntity<?> update
















}
