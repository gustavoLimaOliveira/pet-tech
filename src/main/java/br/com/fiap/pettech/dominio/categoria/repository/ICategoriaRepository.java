package br.com.fiap.pettech.dominio.categoria.repository;

import br.com.fiap.pettech.dominio.categoria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
