package com.nharire.docketapp.app.controller;

import com.nharire.docketapp.app.model.*;
import com.nharire.docketapp.app.model.dto.*;
import com.nharire.docketapp.app.model.dto.response.*;
import com.nharire.docketapp.app.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
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
    public ResponseEntity<AccusedResponse> saveAccusedDetails(@RequestBody AccusedDTO accusedDTO){
        log.info("SAVING ACCUSED DETAILS: {}", accusedDTO.toString());
        AccusedResponse accused = accusedService.saveAccusedDetails(accusedDTO);
        log.info("accused details: {}", accused.toString());
        return new ResponseEntity(accused, HttpStatus.OK);

    }
     @PostMapping("save/address")
    public ResponseEntity<Address> saveAddressDetails(@RequestBody AddressDTO addressDTO){
        log.info("SAVING ADDRESS DETAILS: {}",addressDTO.toString());
        Address address = addressService.saveAddressDetails(addressDTO);
        log.info("address details : {}", address.toString());
        return  new ResponseEntity(address, HttpStatus.OK);

    }
    @PostMapping("save/crime/register")
    public ResponseEntity<CrimeRegisterResponse> saveCrimeRegisterDetails(@RequestBody CrimeRegisterDTO crimeRegisterDTO){
        //
        log.info("SAVE CRIME REGISTER DETAILS: {} ",crimeRegisterDTO.toString());
        CrimeRegisterResponse crimeRegister = crimeRegisterService.saveCrimeRegisterDetails(crimeRegisterDTO);
        log.info("crime register details: {} ", crimeRegister.toString());
       return new ResponseEntity(crimeRegister, HttpStatus.OK);
    }
      @PostMapping("save/complainant")
     public ResponseEntity<ComplainantResponse> saveComplainantDetails(@RequestBody ComplainantDTO complainantDTO){
        log.info("SAVE COMPLAINANT DETAILS: {}" ,complainantDTO.toString());
        ComplainantResponse complainant = complainantService.saveComplainantDetails(complainantDTO);
        log.info("complainant details: {}", complainant.toString());
        return new ResponseEntity(complainant,HttpStatus.OK);

     }
     @PostMapping("save/Next/Of/Kin")
     public ResponseEntity<NextOfKinResponse> saveNextOfKinDetails(@RequestBody @Validated NextOfKinDTO nextOfKinDTO){
        log.info("SAVE NEXT OF KIN DETAILS: {}", nextOfKinDTO.toString());
        NextOfKinResponse nextOfKin = nextOfKinService.saveNextOfKinDetails(nextOfKinDTO);
        log.info("saving next of kin details: {}", nextOfKin.toString());
        return new ResponseEntity(nextOfKin,HttpStatus.OK);
     }
     @PostMapping("/save/police/officer")
     public  ResponseEntity<Officer> saveOfficerDetails(@RequestBody OfficerDTO officerDTO ){
        log.info("SAVE OFFICER DETAILS: {}", officerDTO.toString());
        Officer officer = officerService.saveOfficerDetails(officerDTO);
        log.info("Saving officer details: {}", officer.toString());
        return new ResponseEntity(officer, HttpStatus.OK);
     }
     @PostMapping("save/police/station")
     public ResponseEntity<PoliceStation> savePoliceStationDetails(@RequestBody PoliceStationDTO policeStationDTO){
        log.info("SAVE POLICE STATION DETAILS: {}", policeStationDTO.toString());
        PoliceStation policeStation = policeStationService.savePoliceStationDetails(policeStationDTO);
        log.info("Saving Police station details: {}",policeStation.toString());
        return new ResponseEntity(policeStation,HttpStatus.OK);
    }
    @PostMapping("save/report")
    public ResponseEntity<ReportResponse> saveReportDetails(@RequestBody ReportDTO reportDTO){
        log.info("SAVE REPORT DETAILS: {}", reportDTO.toString());
        ReportResponse report = reportService.saveReportDetails(reportDTO);
        log.info("Saving report details: {}",report.toString());
        return new ResponseEntity(report, HttpStatus.OK);
    }
    @PostMapping("save/reviews")
    public ResponseEntity<Review>  saveReviewDetails(@RequestBody ReviewDTO reviewDTO){
        log.info("SAVE REVIEWS: {}", reviewDTO.toString());
        Review review = reviewService.saveReviews(reviewDTO);
        log.info("Saving reviews: {}", review.toString());
        return new ResponseEntity(review, HttpStatus.OK);
    }
    @PostMapping("save/user")
    public ResponseEntity<UserResponse> saveUserDetails(@RequestBody UserDTO userDTO){
        log.info("SAVE USER DETAILS: {}", userDTO.toString());
        UserResponse user = userService.saveUserDetails(userDTO);
        log.info("Saving user details: {}", user.toString());
        return new ResponseEntity(user, HttpStatus.OK);
    }
    @PostMapping("save/witness")
    public ResponseEntity<WitnessResponse> saveWitnessDetails(@RequestBody WitnessDTO witnessDTO){
        log.info("SAVE WITNESS DETAILS: {}", witnessDTO.toString());
        WitnessResponse witness = witnessService.saveWitnessDetails(witnessDTO);
        log.info("Saving witness details: {}", witness.toString());
        return new ResponseEntity(witness, HttpStatus.OK);
    }

    @GetMapping("get/accused")
    public ResponseEntity<List<Accused>>  getAllAccusedDetails(){
        List<Accused> accusedList = accusedService.getAllAccusedDetails();
        return new ResponseEntity(accusedList, HttpStatus.OK);
    }
    @GetMapping("get/accused/{national_id}")
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
       @GetMapping("get/address/{id}")
    public ResponseEntity<AddressResponse> getAddressDetails(@PathVariable Long id){
        log.info("SEARCHING ADDRESS USING ID: {}",id);
       Optional<AddressResponse> address = addressService.getAddressDetails(id);
        AddressResponse address1;
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
    public ResponseEntity<List<CrimeRegister>> getAllCrimeRegisterDetails(){
        List<CrimeRegister> crimeRegisterList= crimeRegisterService.getAllCrimeRegisterDetails();
        return new ResponseEntity(crimeRegisterList,HttpStatus.OK);
    }
      @GetMapping("/get/crime_register/{crime_Id}")
       public ResponseEntity<CrimeRegister> getCrimeRegisterDetails(@PathVariable Long crimeId){
        log.info("SEARCHING CRIME REGISTER DETAILS USING CRIME ID: {}", crimeId);
        Optional<CrimeRegister> crimeRegister = crimeRegisterService.getCrimeRegisterDetails(crimeId);
        CrimeRegister crimeRegister1;
        if (crimeRegister.isPresent()){
            crimeRegister1 = crimeRegister.get();
        }else {
            throw new RuntimeException("The given crimeId was not found!!!");
        }
        return new ResponseEntity(crimeRegister1,HttpStatus.OK);
    }
    @GetMapping("/get/next_of_kin")
    public ResponseEntity <List<NextOfKin>> getAllNextOfKinDetails(){
        List<NextOfKin> nextOfKinList=nextOfKinService.getAllNextOfKinDetails();
        return new ResponseEntity(nextOfKinList,HttpStatus.OK);
    }
    @GetMapping("/get/next_of_kin/{national_Id}")
    public ResponseEntity<NextOfKin> getNextOfKinDetails(@PathVariable  String nationalId){
        log.info("SEARCHING NEXT OF KIN DETAILS USING NATIONAL ID: {}", nationalId);
        Optional<NextOfKin> nextOfKin = nextOfKinService.getNextOfKinDetails(nationalId);
        NextOfKin nextOfKin1;
        if (nextOfKin.isPresent()){
            nextOfKin1 = nextOfKin.get();
        }else {
            throw new RuntimeException("The given national id is not found!!!");
        }
        return new ResponseEntity(nextOfKin1,HttpStatus.OK);
    }
       @GetMapping("/get/officer/{id}")
        public ResponseEntity<Officer> getOfficerDetails(@PathVariable Long id){
        log.info("SEARCHING OFFICER DETAILS USING ID: {}", id);
        Optional<Officer> officer = officerService.getOfficerDetails(id);
        Officer officer1;
        if (officer.isPresent()){
            officer1 = officer.get();
        }else{
            throw new RuntimeException("The given id was not found!!!");
        }
        return new ResponseEntity(officer1, HttpStatus.OK);
    }
    @GetMapping("/get/officer")
    public ResponseEntity <List<Officer>> getAllOfficers(){
        List<Officer> officerList = officerService.getAllOfficers();
        return new ResponseEntity(officerList, HttpStatus.OK);
    }
    @GetMapping("/get/police_station")
    public ResponseEntity <List<PoliceStation>> getAllPoliceStationDetails(){
        List<PoliceStation> policeStationList = policeStationService.getAllPoliceStations();
        return new ResponseEntity(policeStationList,HttpStatus.OK);
    }
    @GetMapping("/get/police_station/{id}")
    public ResponseEntity<PoliceStation> getPoliceStation(@PathVariable Long id){
        log.info("SEARCHING POLICE STATION DETAILS USING ID: {}",id);
        Optional<PoliceStation> policeStation= policeStationService.getPoliceStation(id);
        PoliceStation policeStation1;
        if (policeStation.isPresent()){
            policeStation1 = policeStation.get();
        }else {
            throw new RuntimeException("The given id was not found!!!");
        }
        return new ResponseEntity(policeStation1, HttpStatus.OK);
    }

    @GetMapping("/get/report")
    public ResponseEntity <List<Report>> getAllReports(){
        List<Report> reportList = reportService.getAllReports();
        return new ResponseEntity(reportList,HttpStatus.OK);
    }
   @GetMapping("/get/report/{id}")
    public ResponseEntity<Report> getReportDetails(@PathVariable Long id){
        log.info("SEARCHING REPORT DETAILS USING ID: {}", id);
        Optional<Report> report = reportService.getReportDetails(id);
        Report report1;
        if(report.isPresent()){
            report1 = report.get();
        }else {
            throw new RuntimeException("The given id was not found!!!");
        }
        return new ResponseEntity(report1, HttpStatus.OK);
    }
    @GetMapping("/get/reviews")
    public ResponseEntity<Review> getAllReviews(){
        List<Review> reviewList = reviewService.getAllReviews();
        return new ResponseEntity(reviewList,HttpStatus.OK);
    }

   @GetMapping("/get/review/{body}")
    public ResponseEntity<Review> getReviews(@PathVariable Long id){
        log.info("SEARCHING REVIEWS USING BODY: {}", id);
        Optional<Review> review =reviewService.getReviews(id);
        Review review1;
        if (review.isPresent()){
            review1= review.get();
        }else {
            throw new RuntimeException("The given body id not valid!!!");
        }
        return new ResponseEntity(review1,HttpStatus.OK);

    }
   @GetMapping("/get/user")
    public ResponseEntity<User>  getAllUser(){
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity(userList,HttpStatus.OK);
    }
    @GetMapping("/get/user/{nationalId}")
    public ResponseEntity<User> getUser(@PathVariable String nationalId){
        log.info("SEARCHING USER USING NATIONAL ID: {}", nationalId);
        Optional<User> user = userService.getUser(nationalId);
        User user1;
        if (user.isPresent()){
            user1= user.get();
        }else {
            throw new RuntimeException("The given national id is not valid!!!");
        }
        return new ResponseEntity(user1, HttpStatus.OK);
    }
    @GetMapping("/get/witness")
    public ResponseEntity<Witness> getAllWitnessDetails(){
        List<Witness> witnessList = witnessService.getAllWitnessDetails();
        return new ResponseEntity(witnessList, HttpStatus.OK);
    }
    @GetMapping("/get/witness/{nationalId}")
    public ResponseEntity<Witness>  getWitnessDetails(@PathVariable String nationalId){
        log.info("SEARCHING WITNESS USING NATIONAL ID: {}", nationalId);
        Optional<Witness>  witness = witnessService.getWitness(nationalId);
        Witness witness1;
        if (witness.isPresent()){
            witness1=witness.get();
        }else {
            throw new RuntimeException("The given national id is not valid!!!");
        }
        return new ResponseEntity(witness1,HttpStatus.OK);
    }
    @PutMapping("put/accused")
    public ResponseEntity<AccusedResponse> updateAccusedDetails(@RequestBody @Validated AccusedDTO accusedDTO){
        log.info("UPDATE ACCUSED DETAILS : {}", accusedDTO.toString());
        AccusedResponse accused = accusedService.updateAccusedDetails(accusedDTO);
        log.info("UPDATING ACCUSED DETAILS: {}", accused.toString());
        return new ResponseEntity(accused,HttpStatus.OK);


    }
    @PutMapping("put/address")
    public ResponseEntity<AddressResponse> updateAddressDetails(@RequestBody @Validated AddressDTO addressDTO) {
        log.info("UPDATE ADDRESS DETAILS : {}", addressDTO.toString());
        AddressResponse address = addressService.updateAddressDetails(addressDTO);
        log.info("UPDATING ACCUSED DETAILS: {}", address.toString());
        return new ResponseEntity(address, HttpStatus.OK);

    }
    @PutMapping("put/complainant")
    public ResponseEntity<ComplainantDTO> updateComplainantDetails(@RequestBody @Validated ComplainantDTO complainantDTO){
        log.info("UPDATE COMPLAINANT DETAILS : {}",complainantDTO.toString());
        ComplainantDTO complainant = complainantService.updateComplainantDetails(complainantDTO);
        log.info("UPDATING COMPLAINANT DETAILS: {}",complainant.toString());
        return new ResponseEntity(complainant,HttpStatus.OK);

    }























    }
