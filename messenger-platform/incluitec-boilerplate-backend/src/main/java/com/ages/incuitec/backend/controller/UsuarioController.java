package com.ages.incuitec.backend.controller;

import com.ages.incuitec.backend.Usuario.Usuario;
import com.ages.incuitec.backend.dto.UsuarioDto;
import com.ages.incuitec.backend.exception.RecursoNaoEncontradoException;
import com.ages.incuitec.backend.mapper.UsuarioMapper;
import com.ages.incuitec.backend.service.FacebookService;
import com.ages.incuitec.backend.service.UsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/usuario/")
public class UsuarioController {

    private final UsuarioService usuarioService;

    private static final Logger log = LogManager.getLogger(FacebookService.class);


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("inserir")
    public ResponseEntity<UsuarioDto> inserirUsuario(@RequestBody UsuarioDto usuarioDto){
        log.info("Salvando usuário: {}", usuarioDto);
        Usuario usuarioSalvo = usuarioService.salvar(usuarioDto);
        log.info("Usuário salvo: {}", usuarioSalvo);
        return ResponseEntity.ok(UsuarioMapper.mapToDto(usuarioSalvo));
    }

    @RequestMapping("buscar")
    public ResponseEntity<UsuarioDto> buscarUsuario(@PathVariable Long id){
        log.info("Iniciando busca por usuário com id {}", id);
        Usuario usuarioSalvo = usuarioService.buscar(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com id "+id+" não encontrado."));
        log.info("Usuário com id {} encontrado", id);
        return ResponseEntity.ok(UsuarioMapper.mapToDto(usuarioSalvo));
    }
}
