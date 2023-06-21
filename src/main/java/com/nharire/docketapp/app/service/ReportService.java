package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.Report;
import com.nharire.docketapp.app.model.dto.ReportDTO;
import com.nharire.docketapp.app.model.dto.response.ReportResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReportService {

    ReportResponse saveReportDetails(ReportDTO reportDTO);

    ReportDTO updateReportDetails(ReportDTO reportDTO);

    void deleteReportById(Long id);

    void deleteCrimeRegister(Long crimeId);

    Report addCrimeRegister (CrimeRegister crimeRegister);

    List<Report> getAllReports();

    Optional<Report> getReportDetails(Long id);



}
