package com.example.demowithtests.web.passportControllers;

import com.example.demowithtests.dto.PassportRequestDto;
import com.example.demowithtests.dto.PassportResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface PassportControllerSwagger extends PassportController {
    @Override
    @Operation(summary = "Endpoint to add a new passport.", description = "Create request to add a new passport.", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new passport is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified passport request not found."),
            @ApiResponse(responseCode = "409", description = "passport already exists")})
    PassportResponseDto savePassport(PassportRequestDto requestForSave);

    @Override
    @Operation(summary = "Endpoint to update some filed of passport", description = "Create request to update field of passport", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. passport was refreshed"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified passport request not found.")})
    PassportResponseDto refreshPassport(Integer id, PassportRequestDto requestForSave);

    @Override
    @Operation(summary = "Endpoint returned a passport by his id.", description = "Create request to read a passport by id", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified passport request not found."),
            @ApiResponse(responseCode = "409", description = "passport already exists")})
    PassportResponseDto getPassportById(Integer id);

    @Override
    @Operation(summary = "Endpoint to delete passport", description = "Create request to delete passport", tags = {"Passport"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK. passport was deleted"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified passport request not found.")})
    void removePassportById(Integer id);
}
