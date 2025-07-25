package com.example.cadastroveiculos.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CriarVeiculoDTO(
    @Schema(description = "Nome do veículo", example = "Civic")
    String veiculo,

    @Schema(description = "Marca do veículo", example = "Honda")
    String marca,

    @Schema(description = "Ano de fabricação", example = "2020")
    Integer ano,

    @Schema(description = "Cor do veículo", example = "Preto")
    String cor,

    @Schema(description = "Descrição adicional", example = "Veículo seminovo em ótimo estado")
    String descricao,

    @Schema(description = "Indica se o veículo foi vendido", example = "false")
    Boolean vendido    ) {
    
}
