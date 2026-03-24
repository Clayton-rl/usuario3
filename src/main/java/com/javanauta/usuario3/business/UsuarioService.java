package com.javanauta.usuario3.business;

import com.javanauta.usuario3.business.converter.UsuarioConverter;
import com.javanauta.usuario3.business.dto.UsuarioDTO;
import com.javanauta.usuario3.infrastructure.entity.Usuario;
import com.javanauta.usuario3.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO dto){
        Usuario entity = usuarioConverter.paraUsuario(dto);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(entity));
    }
}
