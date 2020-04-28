package com.indra.selecao.selecaojava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indra.selecao.selecaojava.entity.Merchant;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long>{}
