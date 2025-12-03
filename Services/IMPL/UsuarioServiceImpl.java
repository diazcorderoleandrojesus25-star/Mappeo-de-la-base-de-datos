package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.Usuario;
import com.Jobxpress.Jobxpress.Repository.UsuarioRepository;
import com.Jobxpress.Jobxpress.Service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Integer id, Usuario usuario) {

        Usuario existing = usuarioRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setNombre(usuario.getNombre());
        existing.setEmail(usuario.getEmail());
        existing.setContrasena(usuario.getContrasena());
        existing.setRol(usuario.getRol());

        return usuarioRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
