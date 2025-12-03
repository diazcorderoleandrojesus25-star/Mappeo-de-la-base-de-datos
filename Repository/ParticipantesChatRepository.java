package com.Jobxpress.Jobxpress.Repository;

import com.Jobxpress.Jobxpress.Entity.ParticipantesChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantesChatRepository extends JpaRepository<ParticipantesChat, Integer> {
}
