package com.crowdfunding.controller;

import com.crowdfunding.entity.Users;
import com.crowdfunding.utils.Constants;
import com.crowdfunding.utils.MD5;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(Constants.API_TEST)
public class TestRestAPI {

    // Testing
    @GetMapping("/hello")
    ResponseEntity<Users> hello() {

        Users danang = Users.builder()
                .name("dadang")
                .email("dadang@test.com")
                .role("user")
                .passwordhash(MD5.encrypt("ekwkkwekjksldf"))
                .create_at(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(danang, HttpStatus.OK);
    }

    @GetMapping("/customHeader")
    ResponseEntity<String> customHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");

        return new ResponseEntity<>("Custom header set", headers, HttpStatus.OK);
    }

    @GetMapping("/age")
    ResponseEntity<String> age(@RequestParam("yearOfBirth") int yearOfBirth) {
        if (isInFuture(yearOfBirth)) {
            return new ResponseEntity<>("Tahun kelahiran tidak boleh di masa depan", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Umurmu adalah " + calculateAge(yearOfBirth), HttpStatus.OK);
    }

    @GetMapping("/halo")
    ResponseEntity<String> helloBro() {
        return ResponseEntity.ok("Hello Bro!");
    }

    private boolean isInFuture(int yearOfBirth) {
        return yearOfBirth == 0 ? true : false;
    }

    private boolean calculateAge(int yearOfBirth) {
        return yearOfBirth >= 0 && yearOfBirth <= 100 ? true : false;
    }

}
