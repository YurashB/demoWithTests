package com.example.demowithtests.web.passportControllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demowithtests.dto.PassportRequestDto;
import com.example.demowithtests.dto.PassportResponseDto;
import com.example.demowithtests.service.PassportService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class PassportControllerBean implements PassportControllerSwagger {

    private final PassportService passportService;

    @Override
    @PostMapping("/passports")
    @ResponseStatus(HttpStatus.CREATED)
    public PassportResponseDto savePassport(@RequestBody PassportRequestDto requestForSave) {
        log.info("start method savePassport with PassportRequestDto" + requestForSave.toString());
        return passportService.create(requestForSave);
    }

    @Override
    @PutMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassportResponseDto refreshPassport(@PathVariable Integer id,
            @RequestBody PassportRequestDto requestForSave) {
        log.info("start of refreshPassport with " + requestForSave);
        return passportService.updateById(id, requestForSave);
    }

    @Override
    @GetMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassportResponseDto getPassportById(@PathVariable Integer id) {
        log.info("start of get passport id");
        PassportResponseDto byId = passportService.getById(id);
        return byId;
    }

    @Override
    @PatchMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePassportById(@PathVariable Integer id) {
        passportService.removeById(id);
    }

    @Override
    @PostMapping("/passports/generate/5")
    @ResponseStatus(HttpStatus.OK)
    public List<PassportResponseDto> generateFiveFreePassports() {
        return passportService.generateFreePassports();
    }

    @Override
    @PatchMapping("/passports/{id}/new")
    @ResponseStatus(HttpStatus.OK)
    public PassportResponseDto addNewActivePassport(@PathVariable("id") Integer prevPassportId,
            @RequestParam String prevPassportStatus) {
        return passportService.addNewActivePassport(prevPassportId, prevPassportStatus);
    }

}
