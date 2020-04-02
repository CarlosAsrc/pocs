package com.ages.incuitec.backend.service;

import com.ages.incuitec.backend.Usuario.Usuario;
import com.ages.incuitec.backend.dto.UsuarioDto;
import com.ages.incuitec.backend.exception.ResourceNotFoundException;
import com.ages.incuitec.backend.mapper.UsuarioMapper;
import com.ages.incuitec.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(UsuarioDto usuarioDto) {
        return usuarioRepository.save(UsuarioMapper.mapToModel(usuarioDto));
    }

    public Usuario buscar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com id "+id+" não encontrado."));
    }
}
