package com.mferreira.coopapi.repository;

import com.mferreira.coopapi.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PautaRepository extends JpaRepository<Pauta, Long> {
    public List<Pauta> findByNomeContaining(String nome);

    List<Pauta> findByActiveTrue();
}
