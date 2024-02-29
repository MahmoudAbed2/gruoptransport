package com.abed23.grupptransport.Model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class VehicleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_name")
    private String vehicleName;
    private String carmodel;
     private String årsmodel;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupModel group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getÅrsmodel() {
        return årsmodel;
    }

    public void setÅrsmodel(String årsmodel) {
        this.årsmodel = årsmodel;
    }
}
