package com.example.cadastroveiculos.repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cadastroveiculos.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
        Long countByVendidoFalse();   
        List<Veiculo> findByCreatedAtAfter(Instant data);
        
        @Query("SELECT (v.ano / 10) * 10 AS decada, COUNT(v) FROM Veiculo v GROUP BY decada ORDER BY decada")
        List<Object[]> countVeiculosPorDecada();

        @Query("SELECT v.marca, COUNT(v) FROM Veiculo v GROUP BY v.marca ORDER BY v.marca")
        List<Object[]> countVeiculosPorMarca();

        @Query("SELECT v FROM Veiculo v WHERE "
        + "(:marca IS NULL OR v.marca = :marca) AND "
        + "(:ano IS NULL OR v.ano = :ano) AND "
        + "(:cor IS NULL OR v.cor = :cor)")
        List<Veiculo> findByMarcaAndAnoAndCor(String marca, Integer ano, String cor);
}
