package com.example.cadastroveiculos.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadastroveiculos.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, UUID> {
    public Optional<Marca> findByNome(String nome);
}
