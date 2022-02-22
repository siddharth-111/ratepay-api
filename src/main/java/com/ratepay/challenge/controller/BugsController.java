package com.ratepay.challenge.controller;

import com.ratepay.challenge.entity.Bug;
import com.ratepay.challenge.exception.BadRequestException;
import com.ratepay.challenge.service.serviceInterface.BugsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class BugsController {

    private final BugsService bugsService;

    @GetMapping("/bugs")
    public ResponseEntity<List<Bug>> getBugs(@RequestParam(required = false) String title) {
        List<Bug> bugList = bugsService.getBugs();
        return new ResponseEntity<>(bugList, OK);
    }

    @GetMapping("/bugs/{id}")
    public ResponseEntity<Bug> getBug(@PathVariable("id") UUID issueId) {

        Bug bug = bugsService.getBugById(issueId);

        return new ResponseEntity<>(bug, HttpStatus.OK);
    }

    @PostMapping("/bugs/")
    public ResponseEntity<Bug> createBug(@RequestBody Bug bug) {
        if(StringUtils.isEmpty(bug))
            throw new BadRequestException("Mandatory field title is missing");

        Bug bugServiceResponse = bugsService.createBug(bug);

        return new ResponseEntity<>(bugServiceResponse, HttpStatus.OK);
    }

    @PatchMapping("/bugs/")
    public ResponseEntity<Bug> updateBug(@RequestBody Bug bug) {

        Bug bugServiceResponse = bugsService.updateBug(bug);

        return new ResponseEntity<>(bugServiceResponse, HttpStatus.OK);
    }

    @DeleteMapping("/bugs/{id}")
    public ResponseEntity<Bug> deleteBug(@PathVariable("id") UUID issueId) {

        bugsService.deleteBug(issueId);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
