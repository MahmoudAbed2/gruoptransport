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

    boolean deleteWalk(Long walkId);

    List<WalkModel> getAllWalks();

    List<WalkModel> getAllWalksForGroup();

    WalkModel getWalkById(Long walkId);

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
                existingWalk.setGroupName(walkDetails.getGroupName());
                existingWalk.setStart(walkDetails.getStart());
                existingWalk.setEnd(walkDetails.getEnd());
                existingWalk.setWalkingTime(walkDetails.getWalkingTime());

                return walkRepository.save(existingWalk);
            } else {
                return null;
            }
        }

        @Override
        public boolean deleteWalk(Long groupId, Long walkId) {
            return false;
        }

        @Override
        public boolean deleteWalk(Long walkId) {
            Optional<WalkModel> walkOptional = walkRepository.findById(walkId);

            if (walkOptional.isPresent()) {
                walkRepository.deleteById(walkId);
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

        @Override
        public WalkModel getWalkById(Long walkId) {
            Optional<WalkModel> walkOptional = walkRepository.findById(walkId);
            return walkOptional.orElse(null);
        }
    }
}

