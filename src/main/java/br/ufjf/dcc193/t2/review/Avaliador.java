package br.ufjf.dcc193.t2.review;

import java.util.List;

import javax.persistence.ElementCollection;
// import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.OneToMany;

@Entity
public class Avaliador
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private String email;
    private String codigoAcesso;
    @ElementCollection
    private List<String> areas;

    // @OneToMany(mappedBy = "avaliador", cascade = CascadeType.ALL)
    // private List<Revisao> revisoes;
    
    public Avaliador()
    {
    }
    public Avaliador(String nome, String email, String codigoAcesso, List<String> areas)
    {
        this.nome = nome;
        this.email = email;
        this.codigoAcesso = codigoAcesso;
        this.areas = areas;
    }
    
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCodigoAcesso()
    {
        return codigoAcesso;
    }
    public void setCodigoAcesso(String codigoAcesso)
    {
        this.codigoAcesso = codigoAcesso;
    }

    public List<String> getAreas()
    {
        return areas;
    }
    public void setAreas(List<String> areas)
    {
        this.areas = areas;
    }

    // public List<Revisao> getRevisoes()
    // {
    //     return revisoes;
    // }
}