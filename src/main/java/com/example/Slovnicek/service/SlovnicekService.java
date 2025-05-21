package com.example.Slovnicek.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service

public class SlovnicekService {
    private final Map<String, String> cmSlovnicek = new HashMap<>();


    public SlovnicekService() {

        cmSlovnicek.put("Bembela", "ňouma, hlupák");
        cmSlovnicek.put("Crbka", "troška, kapka");
        cmSlovnicek.put("Čagan", "hůl");
        cmSlovnicek.put("Dohukaný", "vyplašený, bázlivý");
        cmSlovnicek.put("Enem", "jenom");
        cmSlovnicek.put("Frgál", "typický placatý koláč");
        cmSlovnicek.put("Gaťky", "kalhotky");
        cmSlovnicek.put("Guča", "chomáč");
        cmSlovnicek.put("Haluz", "větev");
        cmSlovnicek.put("Chlpat", "pít");
        cmSlovnicek.put("Kostka", "pecka z ovoce");
        cmSlovnicek.put("Lebeňa", "hlava");
        cmSlovnicek.put("Ogara", "kluk");
        cmSlovnicek.put("Onuca", "cár");
        cmSlovnicek.put("Poznenáhlu", "pomalu");
        cmSlovnicek.put("Rantl", "obrubník");
        cmSlovnicek.put("Skřidla", "poklička");
        cmSlovnicek.put("Škraň", "vnitřek tváře");
        cmSlovnicek.put("Ťula", "truhlík, blázínek");
        cmSlovnicek.put("Zatarmanit", "ztratit, založit někam");
    }

    public Map<String, String> getCmSlovnicek() {
        return cmSlovnicek;

    }

    public ResponseEntity<Map<String, String>> getSlovnicekFiltr(String od, String doP) {
            char odChar = Character.toUpperCase(od.charAt(0));
            char doChar = Character.toUpperCase(doP.charAt(0));

            Map<String, String> filtr = cmSlovnicek.entrySet().stream()
                    .filter(entry -> {
                        String slovo = entry.getKey().toUpperCase();
                        return !slovo.isEmpty() && slovo.charAt(0) >= odChar && slovo.charAt(0) <= doChar;
                    })
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            return new ResponseEntity<>(filtr, HttpStatus.OK);
    }

    public ResponseEntity<String> getPreklad(@RequestBody String slovo) {
        try{
            Map<String, String> slovnicek = getCmSlovnicek();
            for (Map.Entry<String, String> entry : slovnicek.entrySet()) {
                String klic = entry.getKey().trim();
                if (klic.equalsIgnoreCase(slovo.trim())) {
                    return new ResponseEntity<>(entry.getKey() + " = " + entry.getValue(), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Překlad nenalezen.", HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>("Chyba serveru.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}