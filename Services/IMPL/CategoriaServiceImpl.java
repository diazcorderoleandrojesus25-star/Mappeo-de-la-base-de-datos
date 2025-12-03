package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.Categoria;
import com.Jobxpress.Jobxpress.Repository.CategoriaRepository;
import com.Jobxpress.Jobxpress.Service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria update(Integer id, Categoria categoria) {
        Categoria existing = categoriaRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setNombre(categoria.getNombre());

        return categoriaRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
