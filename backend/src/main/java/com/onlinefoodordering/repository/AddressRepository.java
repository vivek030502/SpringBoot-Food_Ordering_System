package com.onlinefoodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodordering.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}