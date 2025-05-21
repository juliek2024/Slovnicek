package com.example.Slovnicek.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Slovnicek.service.SlovnicekService;

import java.util.Map;

@RestController
public class SlovnicekController {

    private final SlovnicekService slovnicekService;

    public SlovnicekController(SlovnicekService slovnicekService1) {
        this.slovnicekService = slovnicekService1;
    }

    @GetMapping("/celyslovnicek")
    public ResponseEntity<Map<String, String>> celySlovnicek() {
        try {
            return new ResponseEntity<>(slovnicekService.getCmSlovnicek(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/slovnicekAE")
    public ResponseEntity<Map<String, String>> slovnicekAE() {
        try {
            ResponseEntity<Map<String, String>>filtr = slovnicekService.getSlovnicekFiltr("A", "E");
            return filtr;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/slovnicekFO")
    public ResponseEntity<Map<String, String>> slovnicekFO() {
        try {
            ResponseEntity<Map<String, String>>filtr = slovnicekService.getSlovnicekFiltr("F", "O");
            return filtr;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/slovnicekPŽ")
    public ResponseEntity<Map<String, String>> slovnicekPŽ() {
        try {
            ResponseEntity<Map<String, String>>filtr = slovnicekService.getSlovnicekFiltr("P", "Ž");
            return filtr;
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/preklad")
    public ResponseEntity<String> preklad(@RequestBody String slovo) {
        return slovnicekService.getPreklad(slovo);
    }
}




