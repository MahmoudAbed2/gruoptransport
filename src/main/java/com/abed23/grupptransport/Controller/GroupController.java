package com.abed23.grupptransport.Controller;
import com.abed23.grupptransport.Model.Entity.GroupModel;
import com.abed23.grupptransport.Model.Entity.VehicleModel;
import com.abed23.grupptransport.Service.GroupService;
import com.abed23.grupptransport.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")

public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private VehicleService vehicleService;
//http://localhost:8081/groups/add_group
    @PostMapping("/add_group")
    public ResponseEntity<GroupModel> addGroup(@RequestBody GroupModel group) {
        GroupModel savedGroup = groupService.addGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroup);
    }

//http://localhost:8081/groups/all_group
    @GetMapping("/all_group")
    public ResponseEntity<List<GroupModel>> getAllGroups() {
        List<GroupModel> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }
//http://localhost:8081/groups/10
    @GetMapping("/{group_id}")
    public ResponseEntity<GroupModel> getGroupById(@PathVariable("group_id") Long groupId) {
        GroupModel group = groupService.getGroupById(groupId);
        if (group != null) {
            return ResponseEntity.ok(group);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//http://localhost:8081/groups/update_group/6
    @PutMapping("/update_group/{group_id}")
    public ResponseEntity<GroupModel> updateGroup(@PathVariable("group_id") Long groupId,
                                                  @RequestBody GroupModel groupDetails) {
        GroupModel updatedGroup = groupService.updateGroup(groupId, groupDetails);
        if (updatedGroup != null) {
            return ResponseEntity.ok(updatedGroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//http://localhost:8081/groups/delete_group/4
    @DeleteMapping("/delete_group/{group_id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("group_id") Long groupId) {
        boolean deleted = groupService.deleteGroup(groupId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}