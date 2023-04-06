package com.crowdfunding.repositories;

import com.crowdfunding.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Optional<Campaign> findCampaignByName(String name);

}
