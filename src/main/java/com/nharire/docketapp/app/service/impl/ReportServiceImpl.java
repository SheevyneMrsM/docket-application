package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.Report;
import com.nharire.docketapp.app.model.dto.ReportDTO;
import com.nharire.docketapp.app.repository.CrimeRegisterRepo;
import com.nharire.docketapp.app.repository.ReportRepo;
import com.nharire.docketapp.app.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepo reportRepo;

    private final CrimeRegisterRepo crimeRegisterRepo;

    @Override
    public Report saveReportDetails(ReportDTO reportDTO) {
        log.info("SAVE REPORT DETAILS: {}",reportDTO.toString());
        Report report = new Report();
        BeanUtils.copyProperties(reportDTO, report);
        log.info("Saving report details: {}", report);
        return reportRepo.save(report);
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
        Report report =reportRepo.getById(crimeRegister.getDateOfReport().getTime());
        report.getCrime().getCrimeId();
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
