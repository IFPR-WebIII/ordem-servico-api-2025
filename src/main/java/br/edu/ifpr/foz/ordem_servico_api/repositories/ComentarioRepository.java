package br.edu.ifpr.foz.ordem_servico_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.foz.ordem_servico_api.models.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    
}
