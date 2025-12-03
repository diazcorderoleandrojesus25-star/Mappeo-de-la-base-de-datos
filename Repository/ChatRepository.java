package com.Jobxpress.Jobxpress.Repository;

import com.Jobxpress.Jobxpress.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
