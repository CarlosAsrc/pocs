package com.ages.incuitec.backend.controller;

import com.ages.incuitec.backend.model.Usuario;
import com.ages.incuitec.backend.dto.UsuarioDto;
import com.ages.incuitec.backend.exception.ResourceNotFoundException;
import com.ages.incuitec.backend.mapper.UsuarioMapper;
import com.ages.incuitec.backend.service.AuthService;
import com.ages.incuitec.backend.service.UsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/user/")
public class UsuarioController {

    private UsuarioService usuarioService;

    private static final Logger log = LogManager.getLogger(AuthService.class);


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("create")
    public ResponseEntity<UsuarioDto> inserirUsuario(@RequestBody UsuarioDto usuarioDto){
        log.info("Salvando usuário: {}", usuarioDto);
        Usuario usuarioSalvo = usuarioService.salvar(usuarioDto);
        log.info("Usuário salvo: {}", usuarioSalvo);
        return ResponseEntity.ok(UsuarioMapper.mapToDto(usuarioSalvo));
    }

    @RequestMapping("{id}")
    public ResponseEntity buscarUsuario(@PathVariable Long id){
        try {
            log.info("Iniciando busca por usuário com id {}", id);
            Usuario usuarioSalvo = usuarioService.buscar(id);
            log.info("Usuário com id {} encontrado", id);
            return ResponseEntity.ok(UsuarioMapper.mapToDto(usuarioSalvo));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
