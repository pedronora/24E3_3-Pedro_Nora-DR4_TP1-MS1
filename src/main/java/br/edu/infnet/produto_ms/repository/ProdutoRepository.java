package br.edu.infnet.produto_ms.repository;

import br.edu.infnet.produto_ms.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
