package com.abed23.grupptransport.Controller;

import com.abed23.grupptransport.Model.Entity.GroupModel;
import com.abed23.grupptransport.Model.Entity.VehicleModel;

import java.util.List;

public class GroupVehicleDTO {
    private GroupModel group;
    private List<VehicleModel> vehicles;

    public GroupVehicleDTO(GroupModel group, List<VehicleModel> vehicles) {
        this.group = group;
        this.vehicles = vehicles;
    }

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }

    public List<VehicleModel> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleModel> vehicles) {
        this.vehicles = vehicles;
    }
}
