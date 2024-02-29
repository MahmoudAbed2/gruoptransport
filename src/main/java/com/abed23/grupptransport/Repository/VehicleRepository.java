package com.abed23.grupptransport.Repository;

import com.abed23.grupptransport.Model.Entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Long> {

}
