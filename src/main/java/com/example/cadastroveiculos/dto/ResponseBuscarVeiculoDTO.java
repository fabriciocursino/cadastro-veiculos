package com.example.cadastroveiculos.dto;

public record ResponseBuscarVeiculoDTO(String veiculo, String marca, Integer ano, String descricao, Boolean vendido) {
    
}
