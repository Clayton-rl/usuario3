package com.javanauta.usuario3.business;

import com.javanauta.usuario3.business.converter.UsuarioConverter;
import com.javanauta.usuario3.business.dto.UsuarioDTO;
import com.javanauta.usuario3.infrastructure.entity.Usuario;
import com.javanauta.usuario3.infrastructure.exception.ConflictException;
import com.javanauta.usuario3.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO dto){
        emailExiste(dto.getEmail());
        dto.setSenha(passwordEncoder.encode(dto.getEmail()));
        Usuario entity = usuarioConverter.paraUsuario(dto);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(entity));
    }

    public void emailExiste(String email){
        try {
            boolean existe = verifivaEmailExiste(email);
            if (existe){
                throw new ConflictException("Email já cadastrado" + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email já cadastrado" + e.getCause());
        }

    }

    public boolean verifivaEmailExiste(String email){
        return usuarioRepository.existsByEmail(email);

    }
}
