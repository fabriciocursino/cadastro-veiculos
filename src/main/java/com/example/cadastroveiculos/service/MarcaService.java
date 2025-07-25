package com.example.cadastroveiculos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastroveiculos.dto.CriarMarcaDTO;
import com.example.cadastroveiculos.entity.Marca;
import com.example.cadastroveiculos.repository.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public Marca criarMarca(CriarMarcaDTO criarMarcaDTO) {
        var novaMarca = new Marca();
        System.out.println(criarMarcaDTO.nome());
        novaMarca.setNome(criarMarcaDTO.nome());
        return marcaRepository.save(novaMarca);
    }
}
