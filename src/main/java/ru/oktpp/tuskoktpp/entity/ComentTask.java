/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author danil
 */
@Entity
@Table(name = "coment_task")
public class ComentTask implements Serializable {

    @Size(max = 2147483647)
    @Column(name = "textcoment")
    private String textcoment;
    @OneToMany(mappedBy = "idtask")
    private Collection<InformUserComent> informUserComentCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_comenttask")
    @SequenceGenerator(sequenceName = "customer_comenttask", allocationSize = 1, name = "customer_comenttask")
    @Column(name = "id")
    private Long id;
    @Column(name = "countsled")
    private Integer countsled;
    @Column(name = "datecoment")
    @Temporal(TemporalType.DATE)
    private Date datecoment;
    @JoinColumn(name = "usercoment", referencedColumnName = "user_id")
    @ManyToOne
    private AppUser usercoment;
    @JoinColumn(name = "idtask", referencedColumnName = "id")
    @ManyToOne
    private Tasktab idtask;

    public ComentTask() {
    }

    public ComentTask(Long id) {
        this.id = id;
    }
    
    public ComentTask(Tasktab idtask, AppUser usercoment, String textcoment, Date datecoment, Integer countsled) {
        this.idtask = idtask;
        this.usercoment = usercoment;
        this.textcoment = textcoment;
        this.datecoment = datecoment;
        this.countsled = countsled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountsled() {
        return countsled;
    }

    public void setCountsled(Integer countsled) {
        this.countsled = countsled;
    }


    public Date getDatecoment() {
        return datecoment;
    }

    public void setDatecoment(Date datecoment) {
        this.datecoment = datecoment;
    }

    public AppUser getUsercoment() {
        return usercoment;
    }

    public void setUsercoment(AppUser usercoment) {
        this.usercoment = usercoment;
    }

    public Tasktab getIdtask() {
        return idtask;
    }

    public void setIdtask(Tasktab idtask) {
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
        if (!(object instanceof ComentTask)) {
            return false;
        }
        ComentTask other = (ComentTask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.oktpp.tuskoktpp.entity.ComentTask[ id=" + id + " ]";
    }

    public String getTextcoment() {
        return textcoment;
    }

    public void setTextcoment(String textcoment) {
        this.textcoment = textcoment;
    }

    @XmlTransient
    public Collection<InformUserComent> getInformUserComentCollection() {
        return informUserComentCollection;
    }

    public void setInformUserComentCollection(Collection<InformUserComent> informUserComentCollection) {
        this.informUserComentCollection = informUserComentCollection;
    }
    
}
