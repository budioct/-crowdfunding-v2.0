package com.crowdfunding.controller;

import com.crowdfunding.service.UsersService;
import com.crowdfunding.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(Constants.API_USER)
public class UsersController {

    @Autowired
    UsersService usersService;

    Map<String, Object> response = new HashMap<>();

    Map<String, Object> restCheckToken = new HashMap<>();


    @GetMapping("getall")
    public ResponseEntity<Map<String, Object>> getAllTest() {

        try {

            response = usersService.getAll();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable(value = "id") final Long id){

        try{

            response = usersService.byId(id);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }



}
