package com.example.elk.repository;

import com.example.elk.models.Efectivo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EfectivoRepository extends JpaRepository<Efectivo, Long>{

    @Query(value = "SELECT e FROM Efectivo e ORDER BY e.denominacion desc")
    public List<Efectivo> findAllBilletes();

    
}
