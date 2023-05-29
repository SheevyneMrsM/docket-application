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
        return null;
    }

    @Override
    public void deleteReportById(Long id) {

    }

    @Override
    public void deleteCrimeRegister(Long crimeId) {

    }

    @Override
    public ReportDTO addCrimeRegister(CrimeRegister crimeRegister) {
        return null;
    }

    @Override
    public List<Report> getAllReports() {
        return null;
    }

    @Override
    public Optional<Report> getReportDetails(Long id) {
        return Optional.empty();
    }

    @Override
    public ReportDTO addReportDetails(Report report) {
        return null;
    }
}
