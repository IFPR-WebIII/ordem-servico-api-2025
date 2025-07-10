package br.edu.ifpr.foz.ordem_servico_api.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpr.foz.ordem_servico_api.models.Comentario;
import br.edu.ifpr.foz.ordem_servico_api.models.OrdemServico;
import br.edu.ifpr.foz.ordem_servico_api.models.StatusOrdemServico;
import br.edu.ifpr.foz.ordem_servico_api.repositories.ComentarioRepository;
import br.edu.ifpr.foz.ordem_servico_api.repositories.OrdemServicoRepository;

@Service
public class OrdemServicoService {

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    @Autowired
    ComentarioRepository comentarioRepository;

    public OrdemServico save(OrdemServico ordemServico) {

        ordemServico.setDataAbertura(LocalDate.now());
        ordemServico.setStatus(StatusOrdemServico.ABERTA);

        return ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServico> findAll() {
        
        return ordemServicoRepository.findAll();
    
    }

    public OrdemServico findById(Integer id) {
        
        return ordemServicoRepository.findById(id).orElse(null);

    }

    public boolean cancel(Integer id) {

        Optional<OrdemServico> os = ordemServicoRepository.findById(id);
        
        if (os.isPresent()) {
            OrdemServico ordemServico = os.get();
            ordemServico.setStatus(StatusOrdemServico.CANCELADA);
            ordemServicoRepository.save(ordemServico);

            return true;
        }
        
        return false;
    }

    public void finish(Integer id) {

        Optional<OrdemServico> os = ordemServicoRepository.findById(id);
        
        if (os.isPresent()) {
            OrdemServico ordemServico = os.get();
            ordemServico.setStatus(StatusOrdemServico.FINALIZADA);
            ordemServico.setDataFinalizacao(LocalDate.now());
            ordemServicoRepository.save(ordemServico);
        }
        
    }

    public Comentario addComment(Integer id, Comentario comentario) {
        
        Optional<OrdemServico> os = ordemServicoRepository.findById(id);

        if (!os.isPresent()) {
            throw new RuntimeException("Ordem de serviço não encontrda");
        }

        OrdemServico ordemServico = os.get();
        comentario.setDataEnvio(LocalDate.now());
        ordemServico.setComentarios(List.of(comentario));
        comentario.setOrdemServico(ordemServico);

        return comentarioRepository.save(comentario);

        // ordemServicoRepository.save(ordemServico);

    }
}
