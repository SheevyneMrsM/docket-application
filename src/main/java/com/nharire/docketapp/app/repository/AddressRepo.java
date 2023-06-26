package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.dto.AddressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
    AddressDTO findAllById(String id);

    @Query("select a from Address a where upper(a.streetAddress) = upper(?1)")
    List<Address> findByStreetAddressEqualsIgnoreCase(String streetAddress);



    List<AddressDTO> findAllById(Long id);
}
