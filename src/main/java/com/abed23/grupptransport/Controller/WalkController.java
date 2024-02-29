package com.abed23.grupptransport.Controller;

import com.abed23.grupptransport.Model.Entity.WalkModel;
import com.abed23.grupptransport.Service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/groups/{group_id}/walks")
public class WalkController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String ROUTE_SEARCH_URL = "https://spring-azure-group3.azurewebsites.net/api/v1/routes/search/";

    @Autowired
    private WalkService walkService;

    @GetMapping
    public ResponseEntity<List<WalkModel>> getAllWalksForGroup() {
        List<WalkModel> walks = walkService.getAllWalksForGroup(); 
        return ResponseEntity.ok(walks);
        }

//http://localhost:8081/groups/6/walks/3
    @GetMapping("/{walk_id}")
    public ResponseEntity<WalkModel> getWalkById(@PathVariable("group_id") Long groupId,
                                                 @PathVariable("walk_id") Long walkId) {
        WalkModel walk = walkService.getWalkById(groupId, walkId);
        if (walk != null) {
            return ResponseEntity.ok(walk);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//http://localhost:8081/groups/5/walks/add_walk
    @PostMapping("/add_walk")
    public ResponseEntity<WalkModel> addWalkToGroup(@PathVariable("group_id") Long groupId,
                                                    @RequestBody WalkModel walk) {
        walk.setGroupId(groupId);
        WalkModel savedWalk = walkService.addWalkToGroup(groupId, walk);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWalk);
    }
//http://localhost:8081/groups/5/walks/1
    @PutMapping("/{walk_id}")
    public ResponseEntity<WalkModel> updateWalk(@PathVariable("group_id") Long groupId,
                                                @PathVariable("walk_id") Long walkId,
                                                @RequestBody WalkModel walkDetails) {
        WalkModel updatedWalk = walkService.updateWalk(groupId, walkId, walkDetails);
        if (updatedWalk != null) {
            return ResponseEntity.ok(updatedWalk);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//http://localhost:8081/groups/5/walks/1
    @DeleteMapping("/{walk_id}")
    public ResponseEntity<?> deleteWalk(@PathVariable("group_id") Long groupId,
                                        @PathVariable("walk_id") Long walkId) {
        boolean deleted = walkService.deleteWalk(groupId, walkId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Category 1
    //https://spring-azure-group3.azurewebsites.net/api/v1/routes
    @GetMapping("/search/{start}/{end}")
    public ResponseEntity<List<WalkModel>> searchRoute(@PathVariable("start") String start,
                                                       @PathVariable("end") String end) {
        String url = ROUTE_SEARCH_URL + start + "/" + end;
        ResponseEntity<List<WalkModel>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<WalkModel>>() {});
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            List<WalkModel> walks = responseEntity.getBody();
            return ResponseEntity.ok(walks);
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
        }
    }
}
