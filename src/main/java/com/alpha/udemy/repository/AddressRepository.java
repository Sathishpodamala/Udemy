package com.alpha.udemy.repository;

import com.alpha.udemy.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
