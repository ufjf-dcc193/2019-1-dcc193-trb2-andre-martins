package br.ufjf.dcc193.t2.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {
    List<Trabalho> findAllByArea(String area);
}