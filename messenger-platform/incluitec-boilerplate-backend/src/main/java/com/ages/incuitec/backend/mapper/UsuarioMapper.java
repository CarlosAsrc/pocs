package com.ages.incuitec.backend.mapper;

import com.ages.incuitec.backend.model.Usuario;
import com.ages.incuitec.backend.dto.UsuarioDto;

public class UsuarioMapper {

    public static Usuario mapToModel(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setNome(usuarioDto.getNome());
        return usuario;
    }

    public static UsuarioDto mapToDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuarioDto.getId());
        usuarioDto.setNome(usuario.getNome());
        return usuarioDto;
    }
}
