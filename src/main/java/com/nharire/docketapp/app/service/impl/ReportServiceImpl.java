package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.Report;
import com.nharire.docketapp.app.model.dto.ReportDTO;
import com.nharire.docketapp.app.model.dto.response.ReportResponse;
import com.nharire.docketapp.app.repository.CrimeRegisterRepo;
import com.nharire.docketapp.app.repository.OfficerRepo;
import com.nharire.docketapp.app.repository.ReportRepo;
import com.nharire.docketapp.app.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepo reportRepo;

    private final CrimeRegisterRepo crimeRegisterRepo;
    private final OfficerRepo officerRepo;

    @Override
    public ReportResponse saveReportDetails(ReportDTO reportDTO) {
        //create new response object
        ReportResponse reportResponse = new ReportResponse();
        try {

            //print report details to the console
            log.info("SAVE REPORT DETAILS: {}", reportDTO.toString());
            //create new crime register object
            CrimeRegister crimeRegister = new CrimeRegister();
            //create new report object
            Report report = new Report();
            //check if dto is not null
            if (reportDTO != null) {
                if (reportDTO.getCrimeId() != null) {
                    Optional<CrimeRegister> crimeRegister1 = crimeRegisterRepo.findById(reportDTO.getCrimeId());

                    if (crimeRegister1.isPresent()) {

                        report.setCrimeId(crimeRegister.getCrimeId());
                        report.setReported(LocalDateTime.now());
                        //
                        BeanUtils.copyProperties(reportDTO, report);
                        log.info("Saving report details: {}", report);
                        try {
                            report = reportRepo.saveAndFlush(report);

                        } catch (Exception exception) {
                            reportResponse.setResponseCode(500);
                            reportResponse.setMessage("Could not save report");
                            return reportResponse;
                        }
                        crimeRegister = crimeRegister1.get();
                        BeanUtils.copyProperties(reportDTO.getCrimeId(), crimeRegister);
                        crimeRegister = crimeRegisterRepo.saveAndFlush(crimeRegister);


                    } else {
                        reportResponse.setResponseCode(404);
                        reportResponse.setMessage("Crime register is not present! please get details!!!");
                    }

                }

                BeanUtils.copyProperties(report, reportResponse);
                reportResponse.setCrimeRegister(crimeRegister);
                reportResponse.setResponseCode(200);
                reportResponse.setMessage("SUCCESS");
                return reportResponse;
            }

        }catch (Exception e){
            log.info("FAILED TO SAVE REPORT, DATABASE ERROR " + e);
            reportResponse.setResponseCode(400);
            reportResponse.setMessage("Failed to Save Information to Database");
            reportResponse.setCode("DM-DB-001");
            reportResponse.setDescription(e.getMessage());
        }

        return reportResponse;
    }

    @Override
    public ReportDTO updateReportDetails(ReportDTO reportDTO) {
        Optional<Report> report = reportRepo.findById(reportDTO.getId());
        Report report1;
        if (report.isPresent()){
            report1 = report.get();
            BeanUtils.copyProperties(reportDTO,report1);
        }else{
            throw new RuntimeException("No details found, cant update!!!");
        }
        BeanUtils.copyProperties(report1,reportDTO);
        return reportDTO;
    }

    @Override
    public void deleteReportById(Long id) {
        reportRepo.deleteById(id);

    }

    @Override
    public void deleteCrimeRegister(Long crimeId) {
        reportRepo.deleteById(crimeId);

    }

    @Override
    public Report addCrimeRegister(CrimeRegister crimeRegister) {

        Report  report = reportRepo.getById(crimeRegister.getCrimeId());
        report.getCrimeId();
        return reportRepo.save(report);
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepo.findAll();
    }


    @Override
    public Optional<Report> getReportDetails(Long id) {
        return reportRepo.findById(id);
    }


}
