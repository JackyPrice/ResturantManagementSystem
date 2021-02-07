package com.JP.ResturantManagementSystem.repository;

import com.JP.ResturantManagementSystem.entity.H2ReservationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface H2ReservationRespository extends CrudRepository<H2ReservationEntity,String> {
    H2ReservationEntity getById(String id);
    List<H2ReservationEntity> findAll();
    void deleteById(String id);
}
