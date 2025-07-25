package com.example.cadastroveiculos.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastroveiculos.dto.AtualizarVeiculoDTO;
import com.example.cadastroveiculos.dto.CriarVeiculoDTO;
import com.example.cadastroveiculos.dto.ResponseBuscarVeiculoDTO;
import com.example.cadastroveiculos.dto.VeiculoPorDecadaDTO;
import com.example.cadastroveiculos.dto.VeiculoPorMarcaDTO;
import com.example.cadastroveiculos.entity.Veiculo;
import com.example.cadastroveiculos.service.VeiculoService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    
    @Autowired
    private VeiculoService veiculoService;

    @PostMapping
    @Operation(summary = "Registra um veículo")     
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody CriarVeiculoDTO criarVeiculoDTO) {
        var novoVeiculo = veiculoService.criarVeiculo(criarVeiculoDTO);
        return ResponseEntity.created(URI.create("/created/"+novoVeiculo.getId())).build();
    }

    @GetMapping
    @Operation(summary = "Busca uma lista de veículos")
    public ResponseEntity<List<Veiculo>> buscarVeiculos(
        @RequestParam(required = false) String marca,
        @RequestParam(required = false) Integer ano,
        @RequestParam(required = false) String cor
    ) {
        var veiculos = veiculoService.buscarVeiculos(marca, ano, cor);
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca por um veículo através do ID")
    public ResponseEntity<ResponseBuscarVeiculoDTO> buscarVeiculo(@PathVariable("id") String id) {
        var veiculo = veiculoService.buscarVeiculoPorId(id);
        return ResponseEntity.ok(veiculo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza totalmente um veículo através do ID")
    public ResponseEntity<Void> atualizarTotal(@PathVariable("id") String id, @RequestBody AtualizarVeiculoDTO atualizarVeiculoDTO) {
        veiculoService.atualizarVeiculo(id, atualizarVeiculoDTO);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza parcialmente um veículo através do ID")
    public ResponseEntity<Void> atualizarParcial(@PathVariable("id") String id, @RequestBody AtualizarVeiculoDTO dto) {
        veiculoService.atualizarVeiculoParcial(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui o registro de um veículo através do ID")
    public ResponseEntity<Void> excluirVeiculo(@PathVariable("id") String id) {
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-nao-vendidos")
    @Operation(summary = "Busca o total de veículos não vendidos")
    public Long buscarTotalNaoVendidos() {
        return veiculoService.contarNaoVendidos();
    }

    @GetMapping("/ultima-semana")
    @Operation(summary = "Busca os veículos registrados na última semana")
    public ResponseEntity<List<Veiculo>> listarVeiculosUltimaSemana() {
        var veiculos = veiculoService.buscarVeiculosUltimaSemana();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/total-decada")
    @Operation(summary = "Busca o total de veículos por década de fabricação")
    public ResponseEntity<List<VeiculoPorDecadaDTO>> listarPorDecada() {
        return ResponseEntity.ok(veiculoService.contarVeiculosPorDecada());
    }

    @GetMapping("/total-marca")
    @Operation(summary = "Busca o total de veículos por marca fabricante")
    public ResponseEntity<List<VeiculoPorMarcaDTO>> listarPorMarca() {
        return ResponseEntity.ok(veiculoService.contarVeiculosPorMarca());
    }

}
