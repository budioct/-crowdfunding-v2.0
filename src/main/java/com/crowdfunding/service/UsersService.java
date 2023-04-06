package com.crowdfunding.service;

import com.crowdfunding.DTO.UsersDTO;
import com.crowdfunding.entity.Users;
import com.crowdfunding.repositories.UsersRepository;
import com.crowdfunding.utils.Constants;
import com.crowdfunding.utils.ConvertJSON;
import com.crowdfunding.utils.MD5;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersDTO usersDTO;

    @Autowired
    Users users;

    @Autowired
    Gson gson;

    @Autowired
    ObjectMapper objectMapper;

    public Map<String, Object> getAll() {

        Map<String, Object> map = new HashMap<>();
        try {
            List<Users> all = usersRepository.findAll();
            if (all.size() > 0) {
                map.put(Constants.LIST, all);
                map.put(Constants.TOTAL, all.size());
                map.put(Constants.STATUS, Constants.SUCCESS);
                map.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
            } else {
                map.put(Constants.STATUS, "User " + Constants.NOTFOUND);
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
            Optional<Users> getid = usersRepository.findById(id);
            if (getid.isPresent()) {
                map.put(Constants.DATA, getid.get());
                map.put(Constants.STATUS, Constants.SUCCESS);
                map.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
            } else {
                map.put(Constants.STATUS, "User " + Constants.NOTFOUND);
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
            Map<String, Object> usermap = (Map<String, Object>) param.get(Constants.DATA);

            String json = ConvertJSON.writeBro(usermap);
            usersDTO = ConvertJSON.readBro(json, UsersDTO.class);

//            Optional<Users> byId = usersRepository.findById(usersDTO.getIduser());
//            if (byId.isPresent()) {
                Optional<Users> byUsername = usersRepository.findUsersByName(usersDTO.getName());
                if (byUsername.isPresent()) {
                    map.put(Constants.STATUS, "Username Sudah Ada Bro, coba yang lain!");
                    map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                } else {
                    Users build = Users.builder()
                            .name(usersDTO.getName())
                            .email(usersDTO.getEmail())
                            .occupation(usersDTO.getOccupation())
                            .avatarfilename(usersDTO.getAvatarfilename())
                            .passwordhash(MD5.encrypt(usersDTO.getPasswordhash()))
                            .role(usersDTO.getRole())
                            .token(usersDTO.getToken())
                            .create_at(LocalDateTime.now())
                            .update_at(LocalDateTime.now())
                            .build();

                    Users cek = usersRepository.save(build);

                    if (cek != null) {
                        map.put(Constants.STATUS, Constants.SUCCESS);
                        map.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
                    } else {
                        map.put(Constants.STATUS, Constants.FAILED);
                        map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                    }

                }
//            } else {
//                map.put(Constants.STATUS, "Users: " + Constants.FAILED);
//                map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
//            }

            return map;
        } catch (JsonProcessingException e) {
            map.put(Constants.STATUS, "Users: " + Constants.FAILED);
            map.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            return map;
            //throw new RuntimeException(e);
        }

    }


}
