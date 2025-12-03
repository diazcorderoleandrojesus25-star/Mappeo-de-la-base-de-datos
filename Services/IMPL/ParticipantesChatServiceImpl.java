package com.Jobxpress.Jobxpress.Service.impl;

import com.Jobxpress.Jobxpress.Entity.ParticipantesChat;
import com.Jobxpress.Jobxpress.Repository.ParticipantesChatRepository;
import com.Jobxpress.Jobxpress.Service.ParticipantesChatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantesChatServiceImpl implements ParticipantesChatService {

    private final ParticipantesChatRepository repository;

    public ParticipantesChatServiceImpl(ParticipantesChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ParticipantesChat> findAll() {
        return repository.findAll();
    }

    @Override
    public ParticipantesChat findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ParticipantesChat save(ParticipantesChat pc) {
        return repository.save(pc);
    }

    @Override
    public ParticipantesChat update(Integer id, ParticipantesChat pc) {
        ParticipantesChat existing = repository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setChat(pc.getChat());
        existing.setUsuario(pc.getUsuario());

        return repository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
