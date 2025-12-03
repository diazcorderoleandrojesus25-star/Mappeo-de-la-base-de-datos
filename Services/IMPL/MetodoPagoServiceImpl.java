package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.MetodoPago;
import com.Jobxpress.Jobxpress.Repository.MetodosPagoRepository;
import com.Jobxpress.Jobxpress.Service.MetodoPagoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodosPagoRepository metodoPagoRepository;

    public MetodoPagoServiceImpl(MetodosPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    @Override
    public List<MetodoPago> findAll() {
        return metodoPagoRepository.findAll();
    }

    @Override
    public MetodoPago findById(Integer id) {
        return metodoPagoRepository.findById(id).orElse(null);
    }

    @Override
    public MetodoPago save(MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    @Override
    public MetodoPago update(Integer id, MetodoPago metodoPago) {
        MetodoPago existing = metodoPagoRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setFormaPago(metodoPago.getFormaPago());
        return metodoPagoRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        metodoPagoRepository.deleteById(id);
    }
}
