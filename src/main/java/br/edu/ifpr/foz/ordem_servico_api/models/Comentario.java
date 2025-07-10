package br.edu.ifpr.foz.ordem_servico_api.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Comentario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    private LocalDate dataEnvio;

    @ManyToOne
    @JsonIgnoreProperties({"comentarios", "cliente"})
    private OrdemServico ordemServico;
}
