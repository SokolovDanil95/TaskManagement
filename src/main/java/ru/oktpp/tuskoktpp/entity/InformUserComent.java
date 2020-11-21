/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danil
 */
@Entity
@Table(name = "inform_user_coment")
public class InformUserComent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_inf_user_coment")
    @SequenceGenerator(sequenceName = "customer_inf_user_coment", allocationSize = 1, name = "customer_inf_user_coment")
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "iduser", referencedColumnName = "user_id")
    @ManyToOne
    private AppUser iduser;
    @JoinColumn(name = "idtask", referencedColumnName = "id")
    @ManyToOne
    private ComentTask idtask;

    public InformUserComent() {
    }

    public InformUserComent(Long id) {
        this.id = id;
    }
    
    public InformUserComent(ComentTask idtask, AppUser iduser, String text) {
        this.idtask = idtask;
        this.iduser = iduser;
        this.text = text;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AppUser getIduser() {
        return iduser;
    }

    public void setIduser(AppUser iduser) {
        this.iduser = iduser;
    }

    public ComentTask getIdtask() {
        return idtask;
    }

    public void setIdtask(ComentTask idtask) {
        this.idtask = idtask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformUserComent)) {
            return false;
        }
        InformUserComent other = (InformUserComent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.oktpp.tuskoktpp.entity.InformUserComent[ id=" + id + " ]";
    }
    
}
