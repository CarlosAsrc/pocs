package com.ages.incuitec.backend.repository;

import com.ages.incuitec.backend.model.Usuario;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    @Query("select id,nome from usuario where nome like :nome")
    Optional<Usuario> buscarPorName(@Param("name") String nome);
}
