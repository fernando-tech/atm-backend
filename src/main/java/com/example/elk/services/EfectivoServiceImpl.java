package com.example.elk.services;

import com.example.elk.dto.RetiroDto;
import com.example.elk.models.Efectivo;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.elk.repository.EfectivoRepository;

@Service
public class EfectivoServiceImpl implements EfectivoService {

    @Autowired
    private EfectivoRepository billeteRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Efectivo> findBilletes() {
        return billeteRepo.findAllBilletes();
    }

    @Override
    @Transactional
    public Map<Float, Integer> retirarEfectivo(RetiroDto data) {
        List<Efectivo> billetes = billeteRepo.findAllBilletes();
        Float efectivo = data.getEfectivo();
        Map<Float, Integer> ticket = new TreeMap<>();

        for (Efectivo b : billetes) {
            if (efectivo > 0) {
                // Si existen billetes de tal denominacion
                if (b.getCantidad() > 0) {
                    // si la cantidad requerida no supera la denominacion
                    int cantidad = 0;
                    if (efectivo >= b.getDenominacion()) {
                        for (int i = 1; i <= b.getCantidad(); i++) {
                            // se valida que la denominacion no supere a lo requerido
                            if (efectivo >= b.getDenominacion()) {
                                efectivo = efectivo - b.getDenominacion();
                                cantidad = i;
                            }
                        }
                        b.setCantidad(b.getCantidad() - cantidad);
                        ticket.put(b.getDenominacion(), cantidad);
                    }
                }
            }
        }
        
        if(efectivo != 0){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo retirar la cantidad requerida");
        }

        return ticket;
    }

}
