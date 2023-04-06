package com.crowdfunding.service;

import com.crowdfunding.DTO.CampaignDTO;
import com.crowdfunding.entity.Campaign;
import com.crowdfunding.repositories.CampaignRepository;
import com.crowdfunding.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    CampaignDTO campaignDTO;

    @Autowired
    Campaign campaign;

    @Autowired
    ObjectMapper objectMapper;

    public Map<String, Object> getAll() {

        Map<String, Object> map = new HashMap<>();
        try {
            List<Campaign> all = campaignRepository.findAll();
            if (all.size() > 0) {
                map.put(Constants.LIST, all);
                map.put(Constants.TOTAL, all.size());
                map.put(Constants.STATUS, Constants.SUCCESS);
                map.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
            } else {
                map.put(Constants.STATUS, "Campaign " + Constants.NOTFOUND);
                map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }
            return map;
        } catch (Exception e) {
            map.put(Constants.STATUS, Constants.FAILED);
            map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return map;
        }

    }

    public Map<String, Object> byId(Long id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Optional<Campaign> getid = campaignRepository.findById(id);
            if (getid.isPresent()) {
                map.put(Constants.DATA, getid.get());
                map.put(Constants.STATUS, Constants.SUCCESS);
                map.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
            } else {
                map.put(Constants.STATUS, "Campaign " + Constants.NOTFOUND);
                map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put(Constants.STATUS, Constants.FAILED);
            map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return map;
        }

    }


}
