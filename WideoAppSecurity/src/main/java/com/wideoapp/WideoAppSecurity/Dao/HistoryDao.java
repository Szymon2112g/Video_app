package com.wideoapp.WideoAppSecurity.Dao;

import com.wideoapp.WideoAppSecurity.Entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HistoryDao extends JpaRepository<History, Long> {

    @Transactional
    List<History> findAllByUserIdOrderByIdDesc(int userId);
}
