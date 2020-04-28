package com.indra.selecao.selecaojava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indra.selecao.selecaojava.entity.PriceHistory;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {}
