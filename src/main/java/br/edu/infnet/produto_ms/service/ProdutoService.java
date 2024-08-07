package br.edu.infnet.produto_ms.service;

import br.edu.infnet.produto_ms.model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    public List<Produto> getAll();

    public Optional<Produto> getById(Long id);

    public Produto update(Long id, Produto produto);

    public Produto save(Produto produto);

    public void delete(Long id);
}
