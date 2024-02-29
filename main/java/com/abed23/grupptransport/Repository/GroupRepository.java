package com.abed23.grupptransport.Repository;
import com.abed23.grupptransport.Model.Entity.GroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Long> {

}