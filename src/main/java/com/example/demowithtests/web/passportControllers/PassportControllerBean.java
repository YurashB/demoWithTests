package com.example.demowithtests.web.passportControllers;

import com.example.demowithtests.dto.PassportRequestDto;
import com.example.demowithtests.dto.PassportResponseDto;
import com.example.demowithtests.service.PassportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class PassportControllerBean implements PassportControllerSwagger{

    private final PassportService passportService;

    @Override
    @PostMapping("/passports")
    @ResponseStatus(HttpStatus.CREATED)
    public PassportResponseDto savePassport(@RequestBody PassportRequestDto requestForSave) {
        log.info("start method savePassport with PassportRequestDto" + requestForSave.toString() );
        return passportService.create(requestForSave);
    }

    @Override
    @PutMapping("/passports/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PassportResponseDto refreshPassport(@PathVariable Integer id, @RequestBody PassportRequestDto requestForSave) {
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
}
