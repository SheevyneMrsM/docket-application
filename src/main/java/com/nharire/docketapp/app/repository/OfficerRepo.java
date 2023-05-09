package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficerRepo extends JpaRepository<Officer,Long> {
}
