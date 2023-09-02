package br.com.fiap.pettech.dominio.endereco.repository;

import br.com.fiap.pettech.dominio.endereco.entitie.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {
}
