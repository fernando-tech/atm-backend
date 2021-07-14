package com.example.elk.controllers;

import com.example.elk.dto.RetiroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.elk.services.EfectivoService;

@RestController
@RequestMapping("/api/atm")
@CrossOrigin
public class CajeroController {

    @Autowired
    private EfectivoService billeteService;

    @PostMapping("/retiro")
    public ResponseEntity<?> retirar(@RequestBody RetiroDto data) {
        
        if(data.getEfectivo() > 12550){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El monto requerido no se encuentra disponible");
        }
        
        return new ResponseEntity<>(billeteService.retirarEfectivo(data), HttpStatus.OK);
    }
}
