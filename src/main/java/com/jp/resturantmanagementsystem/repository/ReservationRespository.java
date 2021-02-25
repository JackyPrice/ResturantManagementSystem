package com.jp.resturantmanagementsystem.repository;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRespository extends CrudRepository<ReservationEntity,String> {
    ReservationEntity getById(String id);
    List<ReservationEntity> findAll();
    void deleteById(String id);
}
