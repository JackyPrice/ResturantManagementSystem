package com.jp.resturantmanagementsystem.repository;

import com.jp.resturantmanagementsystem.entity.DockerReservationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DockerReservationRespository extends CrudRepository<DockerReservationEntity,String> {
    DockerReservationEntity getById(String id);
    List<DockerReservationEntity> findAll();
    void deleteById(String id);
}
