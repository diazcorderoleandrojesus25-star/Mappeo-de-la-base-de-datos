package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> findAll();
    Categoria findById(Integer id);
    Categoria save(Categoria categoria);
    Categoria update(Integer id, Categoria categoria);
    void delete(Integer id);
}
