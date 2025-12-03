package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.Pago;
import com.Jobxpress.Jobxpress.Repository.PagoRepository;
import com.Jobxpress.Jobxpress.Service.PagoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;

    public PagoServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago findById(Integer id) {
        return pagoRepository.findById(id).orElse(null);
    }

    @Override
    public Pago save(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago update(Integer id, Pago pago) {
        Pago existing = pagoRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setMonto(pago.getMonto());
        existing.setFecha(pago.getFecha());
        existing.setMetodo(pago.getMetodo());
        existing.setContratacion(pago.getContratacion());

        return pagoRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        pagoRepository.deleteById(id);
    }
}
