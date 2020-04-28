package com.indra.selecao.selecaojava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indra.selecao.selecaojava.entity.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {}
