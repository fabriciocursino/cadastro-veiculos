package com.example.cadastroveiculos.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.cadastroveiculos.dto.AtualizarVeiculoDTO;
import com.example.cadastroveiculos.dto.ResponseBuscarVeiculoDTO;
import com.example.cadastroveiculos.dto.VeiculoPorDecadaDTO;
import com.example.cadastroveiculos.dto.VeiculoPorMarcaDTO;
import com.example.cadastroveiculos.dto.CriarVeiculoDTO;
import com.example.cadastroveiculos.entity.Veiculo;
import com.example.cadastroveiculos.repository.MarcaRepository;
import com.example.cadastroveiculos.repository.VeiculoRepository;

@Service
public class VeiculoService {
    
    @Autowired
    private VeiculoRepository veiculoRepository;
    
    @Autowired
    private MarcaRepository marcaRepository;

    public Veiculo criarVeiculo(CriarVeiculoDTO criarVeiculoDTO) {
        marcaRepository.findByNome(criarVeiculoDTO.marca())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));
        
        var veiculo = new Veiculo();
        veiculo.setVeiculo(criarVeiculoDTO.veiculo());
        veiculo.setMarca(criarVeiculoDTO.marca());
        veiculo.setAno(criarVeiculoDTO.ano());
        veiculo.setCor(criarVeiculoDTO.cor());
        veiculo.setDescricao(criarVeiculoDTO.descricao());
        veiculo.setVendido(criarVeiculoDTO.vendido());

        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> buscarVeiculos(String marca, Integer ano, String cor) {
        return veiculoRepository.findByMarcaAndAnoAndCor(marca, ano, cor);
    }

    public Long contarNaoVendidos() {
        return veiculoRepository.countByVendidoFalse();
    }

    public List<Veiculo> buscarVeiculosUltimaSemana() {
        Instant seteDiasAtras = Instant.now().minusSeconds(7 * 24 * 60 * 60);
        return veiculoRepository.findByCreatedAtAfter(seteDiasAtras);
    }

    public List<VeiculoPorDecadaDTO> contarVeiculosPorDecada() {
        return veiculoRepository.countVeiculosPorDecada().stream()
            .map(obj -> new VeiculoPorDecadaDTO((Integer) obj[0], (Long) obj[1]))
            .toList();
}

    public List<VeiculoPorMarcaDTO> contarVeiculosPorMarca() {
        return veiculoRepository.countVeiculosPorMarca().stream()
            .map(obj -> new VeiculoPorMarcaDTO((String) obj[0], (Long) obj[1]))
            .toList();
    }

    public ResponseBuscarVeiculoDTO buscarVeiculoPorId(String veiculoId) {
        var veiculo = veiculoRepository.findById(UUID.fromString(veiculoId))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
        
        return new ResponseBuscarVeiculoDTO(
            veiculo.getVeiculo(),
            veiculo.getMarca(),
            veiculo.getAno(),
            veiculo.getDescricao(),
            veiculo.getVendido()
        );
    }

    public void atualizarVeiculo(String id, AtualizarVeiculoDTO atualizarVeiculoDTO) {
        var veiculo = veiculoRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));

        veiculo.setVeiculo(atualizarVeiculoDTO.veiculo());
        veiculo.setMarca(atualizarVeiculoDTO.marca());
        veiculo.setAno(atualizarVeiculoDTO.ano());
        veiculo.setCor(atualizarVeiculoDTO.cor());
        veiculo.setDescricao(atualizarVeiculoDTO.descricao());
        veiculo.setVendido(atualizarVeiculoDTO.vendido());

        veiculoRepository.save(veiculo);
    }

    public void atualizarVeiculoParcial(String id, AtualizarVeiculoDTO dto) {
        var veiculo = veiculoRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));

        if (dto.veiculo() != null) veiculo.setVeiculo(dto.veiculo());
        if (dto.marca() != null) veiculo.setMarca(dto.marca());
        if (dto.ano() != null) veiculo.setAno(dto.ano());
        if (dto.cor() != null) veiculo.setCor(dto.cor());
        if (dto.descricao() != null) veiculo.setDescricao(dto.descricao());
        if (dto.vendido() != null) veiculo.setVendido(dto.vendido());

        veiculoRepository.save(veiculo);
    }

    public void excluirVeiculo(String veiculoId) {
        veiculoRepository.deleteById(UUID.fromString(veiculoId));
    }
}
