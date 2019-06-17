package br.ufjf.dcc193.t2.review;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Revisao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Integer nota;
    private String descricao;
    private Integer status;

    @ManyToOne
    @JoinColumn
    private Avaliador avaliador;

    @ManyToOne
    @JoinColumn
    private Trabalho trabalho;
    
    public Revisao()
    {
    }
    public Revisao(Integer nota, String descricao, Integer status)
    {
        this.nota = nota;
        this.descricao = descricao;
        this.status = status;
    }

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public Integer getNota()
    {
        return nota;
    }
    public void setNota(Integer nota)
    {
        this.nota = nota;
    }

    public String getDescricao()
    {
        return descricao;
    }
    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public Avaliador getAvaliador() {
        return avaliador;
    }
    public void setAvaliador(Avaliador avaliador) {
        this.avaliador = avaliador;
    }
    
    public Trabalho getTrabalho() {
        return trabalho;
    }
    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }
}