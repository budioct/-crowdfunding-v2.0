package com.crowdfunding.controller;

import com.crowdfunding.entity.Users;
import com.crowdfunding.service.UsersService;
import com.crowdfunding.utils.Constants;
import com.crowdfunding.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("save")
    public ResponseEntity<Map<String, Object>> save(@RequestBody final Map<String, Object> param){
        try{
            response = usersService.save(param);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable(value = "id") final Long id){
        try{
            response = usersService.delete(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, Constants.FAILED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }



}
