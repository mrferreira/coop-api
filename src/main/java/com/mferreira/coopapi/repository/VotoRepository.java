package com.mferreira.coopapi.repository;

import com.mferreira.coopapi.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    Voto findByCpfAndSessaoId(String cpf, Long id);

    List<Voto> findBySessaoId(Long sessionId);
}
