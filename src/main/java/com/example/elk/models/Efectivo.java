package com.example.elk.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Efectivo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEfectivo;
    private Float denominacion;
    private Integer Cantidad;
    
}
