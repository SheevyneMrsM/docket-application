package com.nharire.docketapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NextOfKinRepo extends JpaRepository<NextOfKinRepo,String> {

}
