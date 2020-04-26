package com.mferreira.coopapi.repository;

import com.mferreira.coopapi.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    public List<Sessao> findByActiveTrue();
}
