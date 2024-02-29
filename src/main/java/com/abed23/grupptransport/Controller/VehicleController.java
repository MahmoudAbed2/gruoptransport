package com.abed23.grupptransport.Controller;

import com.abed23.grupptransport.Model.Entity.VehicleModel;
import com.abed23.grupptransport.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
//http://localhost:8081/vehicles/all_vehicles
    @GetMapping("/all_vehicles")
    public ResponseEntity<List<VehicleModel>> getAllVehicles() {
        List<VehicleModel> vehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
//http://localhost:8081/vehicles/9
    @GetMapping("/{id}")
    public ResponseEntity<VehicleModel> getVehicleById(@PathVariable Long id) {
        VehicleModel vehicle = vehicleService.getVehicleById(id);
        if (vehicle != null) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//http://localhost:8081/vehicles/add_vehicle
    @PostMapping("/add_vehicle")
    public ResponseEntity<VehicleModel> addVehicle(@RequestBody VehicleModel vehicle) {
        VehicleModel savedVehicle = vehicleService.saveVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }
//http://localhost:8081/vehicles/update_Vehicle/4
    @PutMapping("/update_Vehicle/{id}")
    public ResponseEntity<VehicleModel> updateVehicle(@PathVariable Long id, @RequestBody VehicleModel vehicle) {
        VehicleModel updatedVehicle = vehicleService.updateVehicle(id, vehicle);
        if (updatedVehicle != null) {
            return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by_group/{groupId}")
    public ResponseEntity<List<VehicleModel>> getVehiclesByGroup(@PathVariable Long groupId) {
        List<VehicleModel> vehicles = vehicleService.getVehiclesByGroupId(groupId);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }
//http://localhost:8081/vehicles/delete_vehicle/10
    @DeleteMapping("/delete_vehicle/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{group_id}/vehicles")
    public ResponseEntity<List<VehicleModel>> getVehiclesInGroup(@PathVariable("group_id") Long groupId) {
        List<VehicleModel> vehicles = vehicleService.getVehiclesByGroupId(groupId);

        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @PostMapping("/{group_id}/assign_vehicle/{vehicle_id}")
    public ResponseEntity<String> assignVehicleToGroup(@PathVariable("group_id") Long groupId,
                                                       @PathVariable("vehicle_id") Long vehicleId,
                                                       @RequestParam("expected_duration") int expectedDuration) {

        VehicleModel vehicle = vehicleService.getVehicleById(vehicleId);
        if (vehicle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
        }


        vehicleService.updateVehicle(vehicleId, vehicle);

        return ResponseEntity.status(HttpStatus.OK).body("Vehicle assigned to group " + groupId);
    }


}

