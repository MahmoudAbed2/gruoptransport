package com.abed23.grupptransport.Service;

import com.abed23.grupptransport.Model.Entity.GroupModel;
import com.abed23.grupptransport.Repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    List<GroupModel> getAllGroups();

    GroupModel getGroupById(Long groupId);

    GroupModel updateGroup(Long groupId, GroupModel groupDetails);

    boolean deleteGroup(Long groupId);

    GroupModel addGroup(GroupModel group);

    @Service
    public class GroupServiceImpl implements GroupService {

        @Autowired
        private GroupRepository groupRepository;

        @Override
        public List<GroupModel> getAllGroups() {
            return groupRepository.findAll();
        }

        @Override
        public GroupModel getGroupById(Long groupId) {
            Optional<GroupModel> group = groupRepository.findById(groupId);
            return group.orElse(null);
        }

        @Override
        public GroupModel updateGroup(Long groupId, GroupModel groupDetails) {
            Optional<GroupModel> optionalGroup = groupRepository.findById(groupId);
            if (optionalGroup.isPresent()) {
                GroupModel existingGroup = optionalGroup.get();
                existingGroup.setGroupName(groupDetails.getGroupName());

                return groupRepository.save(existingGroup);
            } else {
                return null;
            }
        }

        @Override
        public boolean deleteGroup(Long groupId) {
            Optional<GroupModel> optionalGroup = groupRepository.findById(groupId);
            if (optionalGroup.isPresent()) {
                groupRepository.delete(optionalGroup.get());
                return true;
            } else {
                return false;
            }
        }


        @Override
        public GroupModel addGroup(GroupModel group) {
            return groupRepository.save(group);


        }
    }
}
