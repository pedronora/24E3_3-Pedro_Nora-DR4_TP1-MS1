package br.edu.infnet.produto_ms.service.impl;

import br.edu.infnet.produto_ms.exception.ResourceNotFoundException;
import br.edu.infnet.produto_ms.model.Produto;
import br.edu.infnet.produto_ms.repository.ProdutoRepository;
import br.edu.infnet.produto_ms.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Optional<Produto> getById(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Não encontrado o produto com id: " + id);
        }
        return produto;
    }

    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto update(Long id, Produto produto) {
        Optional<Produto> produtoOptional = getById(id);

        if (produtoOptional.isEmpty()) {
            throw new ResourceNotFoundException("Não encontrado produto com id " + id);
        }

        Produto existingProduto = produtoOptional.get();
        existingProduto.setNome(produto.getNome());
        existingProduto.setQuantidade(produto.getQuantidade());
        existingProduto.setPreco(produto.getPreco());

        return produtoRepository.save(existingProduto);
    }

    @Override
    public void delete(Long id) {
        Optional<Produto> produto = getById(id);

        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Não encontrado produto com id " + id);
        }
        produtoRepository.deleteById(id);
    }
}
