package com.indra.selecao.selecaojava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indra.selecao.selecaojava.DTO.PriceHistoryDTO;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistoryDTO, Long> {}
