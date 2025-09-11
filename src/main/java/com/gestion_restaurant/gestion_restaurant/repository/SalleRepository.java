package com.gestion_restaurant.gestion_restaurant.repository;

import com.gestion_restaurant.gestion_restaurant.DTO.TablesDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

public interface SalleRepository extends JpaRepository<Salle,Long> {
    @Query("select t from Salle s join tables t where t.nbre_place = :nbre_place")
    public ResponseEntity<TablesDtoResponse> getTableByNbre_Place(@Param("nbre_place") Long nbre_place);
}
