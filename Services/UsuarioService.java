package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> findAll();

    Usuario findById(Integer id);

    Usuario save(Usuario usuario);

    Usuario update(Integer id, Usuario usuario);

    void delete(Integer id);

    // 🔥 MÉTODO NECESARIO PARA LOGIN Y PERFIL
    Usuario findByEmail(String email);
}
