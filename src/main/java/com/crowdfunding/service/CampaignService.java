package com.crowdfunding.service;

import com.crowdfunding.DTO.CampaignDTO;
import com.crowdfunding.entity.Campaign;
import com.crowdfunding.entity.Users;
import com.crowdfunding.repositories.CampaignRepository;
import com.crowdfunding.repositories.UsersRepository;
import com.crowdfunding.utils.Constants;
import com.crowdfunding.utils.ConvertJSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CampaignService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    CampaignDTO campaignDTO;

    @Autowired
    Users users;

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

    public Map<String, Object> save(Map<String, Object> param) {
        Map<String, Object> map = new HashMap<>();

        try {
            Map<String, Object> campaignmap = (Map<String, Object>) param.get(Constants.DATA);

            String json = ConvertJSON.writeBro(campaignmap);
            campaignDTO = ConvertJSON.readBro(json, CampaignDTO.class);

            //Optional<Campaign> getid = campaignRepository.findById(campaignDTO.getId_user());
            Optional<Users> getid = usersRepository.findById(campaignDTO.getIduser());
            if (getid.isPresent()) {
                Optional<Campaign> byName = campaignRepository.findCampaignByName(campaignDTO.getName());
                if (byName.isPresent()) {
                    map.put(Constants.STATUS, "Nama Campaign Sudah Ada Bro, coba yang lain!");
                    map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                } else {

                    users = Users.builder()
                            .iduser(getid.get().getIduser())
                            .build();

                    campaign = Campaign.builder()
                            .users(users)
                            .name(campaignDTO.getName())
                            .shortdescription(campaignDTO.getShortdescription())
                            .description(campaignDTO.getDescription())
                            .goalamount(campaignDTO.getGoalamount())
                            .currentamount(campaignDTO.getCurrentamount())
                            .perks(campaignDTO.getPerks())
                            .backercount(campaign.getBackercount())
                            .slug(campaignDTO.getSlug())
                            .create_at(LocalDateTime.now())
                            .update_at(LocalDateTime.now())
                            .build();

                    Campaign cek = campaignRepository.save(campaign);

                    if (cek != null) {
                        map.put(Constants.STATUS, Constants.SUCCESS);
                        map.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                    } else {
                        map.put(Constants.STATUS, Constants.FAILED);
                        map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                    }
                }
            } else {
                map.put("ID Tidak Ditemuak", "Bro");
                map.put(Constants.STATUS, "Campaign: " + Constants.FAILED);
                map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            }
            return map;
        } catch (JsonProcessingException e) {
            map.put(Constants.STATUS, "Campaign: " + Constants.FAILED);
            map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return map;
//            throw new RuntimeException(e);
        }
    }


}
