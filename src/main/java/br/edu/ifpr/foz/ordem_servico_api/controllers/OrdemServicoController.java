package br.edu.ifpr.foz.ordem_servico_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.foz.ordem_servico_api.models.Comentario;
import br.edu.ifpr.foz.ordem_servico_api.models.OrdemServico;
import br.edu.ifpr.foz.ordem_servico_api.services.OrdemServicoService;


@RestController
@RequestMapping("/ordens")
public class OrdemServicoController {

    @Autowired
    OrdemServicoService ordemServicoService;
 
    @PostMapping
    public ResponseEntity<OrdemServico> save(@RequestBody OrdemServico ordemServico){
        OrdemServico os = ordemServicoService.save(ordemServico);

        return ResponseEntity.status(HttpStatus.CREATED).body(os);

    }

    @GetMapping
    public ResponseEntity<List<OrdemServico>> findAll(){
        
        List<OrdemServico> ordens = ordemServicoService.findAll();
        return ResponseEntity.ok(ordens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> findById(@PathVariable Integer id){

        OrdemServico os = ordemServicoService.findById(id);

        if (os == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(os);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancel(@PathVariable Integer id){
        
        Boolean cancelada = ordemServicoService.cancel(id);

        if (cancelada) {
            return ResponseEntity.noContent().build(); //204 sucesso, mas sem conteudo
        } else {
            return ResponseEntity.notFound().build(); //404 recurso não encontrado
        }
    }

    @PutMapping("/{id}/finalizar")
    public void finish(@PathVariable Integer id){
        ordemServicoService.finish(id);
    }

    @PostMapping("/{id}/comentario")
    public Comentario addComment(@PathVariable Integer id, @RequestBody Comentario comentario){

        return ordemServicoService.addComment(id, comentario);
    }
    
}
