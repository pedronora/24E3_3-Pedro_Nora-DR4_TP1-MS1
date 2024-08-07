package br.edu.infnet.produto_ms.controller;

import br.edu.infnet.produto_ms.exception.ResourceNotFoundException;
import br.edu.infnet.produto_ms.model.Produto;
import br.edu.infnet.produto_ms.payload.DetailPayload;
import br.edu.infnet.produto_ms.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAll() {
        return produtoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Optional<Produto> produto = produtoService.getById(id);
            return ResponseEntity.ok().body(produto.get());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetailPayload(e.getMessage()));
        }
    }

    @PostMapping
    public Produto save(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Produto produto, @PathVariable Long id) {
        try {
            Produto updateProduto = produtoService.update(id, produto);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(updateProduto);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetailPayload(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            produtoService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(new DetailPayload("Deletado com Sucesso: Produto " + id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DetailPayload(e.getMessage()));
        }
    }
}
