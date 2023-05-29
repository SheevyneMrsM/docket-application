package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.Report;
import com.nharire.docketapp.app.model.dto.ReportDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReportService {

    Report saveReportDetails(ReportDTO reportDTO);

    ReportDTO updateReportDetails(ReportDTO reportDTO);

    void deleteReportById(Long id);

    void deleteCrimeRegister(Long crimeId);

    ReportDTO addCrimeRegister (CrimeRegister crimeRegister);

    List<Report> getAllReports();

    Optional<Report> getReportDetails(Long id);

    ReportDTO addReportDetails(Report report);


}
