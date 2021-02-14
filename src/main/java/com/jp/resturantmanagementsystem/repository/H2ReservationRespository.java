package com.jp.resturantmanagementsystem.repository;

import com.jp.resturantmanagementsystem.entity.H2ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface H2ReservationRespository extends CrudRepository<H2ReservationEntity,String> {
    H2ReservationEntity getById(String id);
    List<H2ReservationEntity> findAll();
    void deleteById(String id);
}
