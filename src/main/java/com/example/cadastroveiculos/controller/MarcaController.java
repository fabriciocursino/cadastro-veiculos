package com.example.cadastroveiculos.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastroveiculos.dto.CriarMarcaDTO;
import com.example.cadastroveiculos.entity.Marca;
import com.example.cadastroveiculos.service.MarcaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;
    
    @PostMapping
    @Operation(summary = "Registra nova marca para ser utilizada")
    public ResponseEntity<Marca> criarMarca(@RequestBody CriarMarcaDTO criarMarcaDTO) {
        var novaMarca = marcaService.criarMarca(criarMarcaDTO);
        return ResponseEntity.created(URI.create("/marcas/" + novaMarca.getId())).body(novaMarca);
    }
}
