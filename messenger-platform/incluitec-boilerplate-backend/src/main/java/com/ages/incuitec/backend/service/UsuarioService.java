package com.ages.incuitec.backend.service;

import com.ages.incuitec.backend.Usuario.Usuario;
import com.ages.incuitec.backend.dto.UsuarioDto;
import com.ages.incuitec.backend.mapper.UsuarioMapper;
import com.ages.incuitec.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(UsuarioDto usuarioDto) {
        return usuarioRepository.save(UsuarioMapper.mapToModel(usuarioDto));
    }

    public Optional<Usuario> buscar(Long id) {
        return usuarioRepository.findById(id);
    }
}
