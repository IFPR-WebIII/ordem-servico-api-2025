package br.edu.ifpr.foz.ordem_servico_api.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    private Double preco;

    private LocalDate dataAbertura;

    private LocalDate dataFinalizacao;

    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("ordemServico")
    private List<Comentario> comentarios;

}
