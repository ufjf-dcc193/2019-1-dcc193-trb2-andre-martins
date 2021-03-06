package br.ufjf.dcc193.t2.review;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

@Entity
public class Trabalho
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;
    private String descricao;
    private String link;
    private String area;

    @OneToMany(mappedBy = "trabalho", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Revisao> revisoes;

    @Formula("(select count(*) from revisao r where r.trabalho_id = id)")
    private Integer quantRevisoes;

    public Trabalho()
    {
    }
    public Trabalho(String titulo, String descricao, String link, String area)
    {
        this.titulo = titulo;
        this.descricao = descricao;
        this.link = link;
        this.area = area;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }

    public List<Revisao> getRevisoes()
    {
        return revisoes;
    }

    @Transient
    public Integer getQuantRevisoes()
    {
        return quantRevisoes;
    }
}