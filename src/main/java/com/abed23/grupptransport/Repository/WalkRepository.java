package com.abed23.grupptransport.Repository;

import com.abed23.grupptransport.Model.Entity.WalkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalkRepository extends JpaRepository<WalkModel, Long> {
    List<WalkModel> findByGroupId(Long groupId);


    Optional<WalkModel> findByIdAndGroupId(Long walkId, Long groupId);
}
