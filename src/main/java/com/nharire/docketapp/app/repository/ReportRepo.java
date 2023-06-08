package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends JpaRepository<Report,Long> {
    Report getById(Long id);

}
