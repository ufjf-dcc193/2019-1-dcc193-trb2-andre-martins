package br.ufjf.dcc193.t2.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisaoRepository extends JpaRepository<Revisao, Long>
{
    List<Revisao> findAllByAvaliadorId(Long id);
}