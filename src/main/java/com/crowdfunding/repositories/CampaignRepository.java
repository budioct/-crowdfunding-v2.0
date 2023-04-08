package com.crowdfunding.repositories;

import com.crowdfunding.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findCampaignByName(String name);

    @Query(value = "SELECT COUNT(id_user) AS COUNT FROM campaign WHERE id_user = :iduser:" ,nativeQuery = true)
    Integer cekUsers(@Param(value = "iduser") Long id);


}
