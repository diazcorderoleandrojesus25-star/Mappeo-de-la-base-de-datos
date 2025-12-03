package com.Jobxpress.Jobxpress.Service;

import com.Jobxpress.Jobxpress.Entity.ParticipantesChat;
import java.util.List;

public interface ParticipantesChatService {
    List<ParticipantesChat> findAll();
    ParticipantesChat findById(Integer id);
    ParticipantesChat save(ParticipantesChat pc);
    ParticipantesChat update(Integer id, ParticipantesChat pc);
    void delete(Integer id);
}
