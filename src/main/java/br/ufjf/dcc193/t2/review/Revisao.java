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
    private String descrição;
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
    public Revisao(Integer nota, String descrição, Integer status)
    {
        this.nota = nota;
        this.descrição = descrição;
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

    public String getDescrição()
    {
        return descrição;
    }
    public void setDescrição(String descrição)
    {
        this.descrição = descrição;
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