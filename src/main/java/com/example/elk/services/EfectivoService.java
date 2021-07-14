package com.example.elk.services;

import com.example.elk.dto.RetiroDto;
import com.example.elk.models.Efectivo;
import java.util.List;
import java.util.Map;

public interface EfectivoService {
    
    public List<Efectivo> findBilletes();

    public Map<Float, Integer> retirarEfectivo(RetiroDto data);


}
