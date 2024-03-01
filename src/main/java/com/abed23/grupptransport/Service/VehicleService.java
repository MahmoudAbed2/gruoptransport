package com.abed23.grupptransport.Service;


import com.abed23.grupptransport.Model.Entity.VehicleModel;
import com.abed23.grupptransport.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<VehicleModel> getAllVehicles();

    VehicleModel getVehicleById(Long id);

    VehicleModel saveVehicle(VehicleModel vehicle);

    VehicleModel updateVehicle(Long id, VehicleModel vehicle);

    void deleteVehicle(Long id);

    List<VehicleModel> getVehiclesByGroupId(Long groupId);
}
@Service
class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<VehicleModel> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public VehicleModel getVehicleById(Long id) {
        Optional<VehicleModel> vehicleOptional = vehicleRepository.findById(id);
        return vehicleOptional.orElse(null);
    }

    @Override
    public VehicleModel saveVehicle(VehicleModel vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public VehicleModel updateVehicle(Long id, VehicleModel updatedVehicle) {
        VehicleModel existingVehicle = vehicleRepository.findById(id).orElse(null);
        if (existingVehicle != null) {
            // Uppdatera alla relevanta fält
            existingVehicle.setVehicleName(updatedVehicle.getVehicleName());
            existingVehicle.setCarmodel(updatedVehicle.getCarmodel());
            existingVehicle.setÅrsmodel(updatedVehicle.getÅrsmodel());
            // Uppdatera andra fält om det behövs
            // ...

            return vehicleRepository.save(existingVehicle);
        }
        return null;
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<VehicleModel> getVehiclesByGroupId(Long groupId) {
        return null;
    }
}