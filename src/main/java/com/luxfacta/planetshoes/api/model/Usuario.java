
package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USUARIO")
@PageModel.SortField(fieldName = "usuNome")
public class Usuario extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USU_ID")
    @Mapper.CryptoRequired
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO",allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "USU_NOME")
    private String usuNome;
    
    @Basic(optional = false)
    @Column(name = "USU_EMAIL")
    private String usuEmail;
    
    @Basic(optional = false)
    @Column(name = "USU_SENHA")
    @Mapper.IgnoreRest
    private String usuSenha;
    
    @Column(name = "USU_TOKEN")
    @Mapper.IgnoreRest
    private String usuToken;
    
    @Column(name = "USU_TOKEN_EXPIRACAO")
    @Temporal(TemporalType.TIMESTAMP)
    @Mapper.IgnoreRest
    private Date usuTokenExpiracao;
    
    @Basic(optional = false)
    @Column(name = "USU_DATA_CRIACAO", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Mapper.IgnoreRest
    private Date usuDataCriacao;
    
    @Column(name = "USU_DATA_EXCLUSAO")
    @Temporal(TemporalType.TIMESTAMP)
    @Mapper.IgnoreRest
    private Date usuDataExclusao;//

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USUARIO_LOJA",
    joinColumns = @JoinColumn(name = "USU_ID"),
    inverseJoinColumns = @JoinColumn(name = "LOJ_ID"))
    private Set<Loja> lojaList = new HashSet<>();
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefoneList;
    
    @JoinColumn(name = "GRU_ID", referencedColumnName = "GRU_ID", updatable = false, insertable = false)
    @ManyToOne
    private Grupo grupo;

    @Mapper.CryptoRequired
    @Column(name = "GRU_ID")
    private Long gruId;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuNome() {
        return usuNome;
    }

    public void setUsuNome(String usuNome) {
        this.usuNome = usuNome;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public String getUsuSenha() {
        return usuSenha;
    }

    public void setUsuSenha(String usuSenha) {
        this.usuSenha = usuSenha;
    }

    public String getUsuToken() {
        return usuToken;
    }

    public void setUsuToken(String usuToken) {
        this.usuToken = usuToken;
    }

    public Date getUsuTokenExpiracao() {
        return usuTokenExpiracao;
    }

    public void setUsuTokenExpiracao(Date usuTokenExpiracao) {
        this.usuTokenExpiracao = usuTokenExpiracao;
    }

    public Date getUsuDataCriacao() {
        return usuDataCriacao;
    }

    public void setUsuDataCriacao(Date usuDataCriacao) {
        this.usuDataCriacao = usuDataCriacao;
    }

    public Date getUsuDataExclusao() {
        return usuDataExclusao;
    }

    public void setUsuDataExclusao(Date usuDataExclusao) {
        this.usuDataExclusao = usuDataExclusao;
    }

    public List<Telefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<Telefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Set<Loja> getLojaList() {
        return lojaList;
    }

    public void setLojaList(Set<Loja> lojas) {
        this.lojaList = lojas;
    }

    public Long getGruId() {
        return gruId;
    }

    public void setGruId(Long grupoId) {
        this.gruId = grupoId;
    }
}
