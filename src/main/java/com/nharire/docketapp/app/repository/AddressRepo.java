package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
    AddressDTO findAllById(String id);

    List<AddressDTO> findAllById(Long id);
}
