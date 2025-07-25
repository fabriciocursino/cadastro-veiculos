package com.example.cadastroveiculos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import com.example.cadastroveiculos.entity.Veiculo;
import com.example.cadastroveiculos.repository.VeiculoRepository;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {

    @InjectMocks
    private VeiculoService veiculoService;
    
    @Mock
    private VeiculoRepository veiculoRepository;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    private ArgumentCaptor<Integer> integerArgumentCaptor;
    
    @Nested
    class buscarVeiculos {

        @Test
        @Description("Deve retornar todos os veículos da base")
        void deveRetornarTodosOsVeiculos() {
            //arrange
            var veiculo = new Veiculo(
                UUID.randomUUID(),
                "Fit",
                "Honda",
                2020,
                "Amarelo",
                "está com problema no motor",
                false,
                Instant.now(),
                Instant.now()
            );

            var veiculos = List.of(veiculo);
            doReturn(veiculos).when(veiculoRepository).findByMarcaAndAnoAndCor(
                stringArgumentCaptor.capture(),
                integerArgumentCaptor.capture(),
                stringArgumentCaptor.capture()
            );
            //act
            var output = veiculoService.buscarVeiculos(null, null, null);
            //assert
            assertEquals(veiculos.size(), output.size());
        }
    }
}
