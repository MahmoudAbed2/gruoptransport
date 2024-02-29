package com.abed23.grupptransport.Service;

import com.abed23.grupptransport.Model.Entity.WalkModel;
import com.abed23.grupptransport.Repository.WalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface WalkService {
    List<WalkModel> getAllWalksForGroup(Long groupId);

    WalkModel getWalkById(Long groupId, Long walkId);

    WalkModel addWalkToGroup(Long groupId, WalkModel walk);

    WalkModel updateWalk(Long groupId, Long walkId, WalkModel walkDetails);

    boolean deleteWalk(Long groupId, Long walkId);

    List<WalkModel> getAllWalks();

    List<WalkModel> getAllWalksForGroup();

    @Service
    public class WalkServiceImpl implements WalkService {

        @Autowired
        private WalkRepository walkRepository;

        @Override
        public List<WalkModel> getAllWalksForGroup(Long groupId) {
            return walkRepository.findByGroupId(groupId);
        }

        @Override
        public WalkModel getWalkById(Long groupId, Long walkId) {
            Optional<WalkModel> walk = walkRepository.findByIdAndGroupId(walkId, groupId);
            return walk.orElse(null);
        }

        @Override
        public WalkModel addWalkToGroup(Long groupId, WalkModel walk) {
            walk.setGroupId(groupId);
            return walkRepository.save(walk);
        }

        @Override
        public WalkModel updateWalk(Long groupId, Long walkId, WalkModel walkDetails) {
            Optional<WalkModel> optionalWalk = walkRepository.findByIdAndGroupId(walkId, groupId);
            if (optionalWalk.isPresent()) {
                WalkModel existingWalk = optionalWalk.get();
                existingWalk.setDescription(walkDetails.getDescription());

                return walkRepository.save(existingWalk);
            } else {
                return null;
            }
        }

        @Override
        public boolean deleteWalk(Long groupId, Long walkId) {
            Optional<WalkModel> optionalWalk = walkRepository.findByIdAndGroupId(walkId, groupId);
            if (optionalWalk.isPresent()) {
                walkRepository.delete(optionalWalk.get());
                return true;
            } else {
                return false;
            }
        }

        public List<WalkModel> getAllWalks() {
            return walkRepository.findAll();
        }

        @Override
        public List<WalkModel> getAllWalksForGroup() {
            return null;
        }
    }
}