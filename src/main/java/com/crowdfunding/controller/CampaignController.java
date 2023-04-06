package com.crowdfunding.controller;

import com.crowdfunding.service.CampaignService;
import com.crowdfunding.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(Constants.API_CAMPAIGN)
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    Map<String, Object> response = new HashMap<>();

    Map<String, Object> restCheckToken = new HashMap<>();

    @GetMapping("getall")
    public ResponseEntity<Map<String, Object>> getAllTest(){
        try{
            response = campaignService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable(value = "id") Long id){
        try{
            response = campaignService.byId(id);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e){
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody final Map<String, Object> param){
        try{
            response = campaignService.save(param);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }




}
