/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author danil
 */
@Entity
@Table(name = "statetask")
public class Statetask implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "idsost")
    private Collection<StagesProect> stagesProectCollection;
    @OneToMany(mappedBy = "idsost")
    private Collection<Proect> proectCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_state_task")
    @SequenceGenerator(sequenceName = "customer_state_task", allocationSize = 1, name = "customer_state_task")
    @NotNull
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "state")
    private Collection<Tasktab> tasktabCollection;
    @Column(name = "identifier")
    private Integer identifier;

    public Statetask() {
    }

    public Statetask(Long id) {
        this.id = id;
    }

    public Statetask(Long id, String name, Integer identifier) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @XmlTransient
    public Collection<Tasktab> getTasktabCollection() {
        return tasktabCollection;
    }

    public void setTasktabCollection(Collection<Tasktab> tasktabCollection) {
        this.tasktabCollection = tasktabCollection;
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
        if (!(object instanceof Statetask)) {
            return false;
        }
        Statetask other = (Statetask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.oktpp.tuskoktpp.entity.Statetask[ id=" + id + " ]";
    }


    @XmlTransient
    public Collection<Proect> getProectCollection() {
        return proectCollection;
    }

    public void setProectCollection(Collection<Proect> proectCollection) {
        this.proectCollection = proectCollection;
    }


    @XmlTransient
    public Collection<StagesProect> getStagesProectCollection() {
        return stagesProectCollection;
    }

    public void setStagesProectCollection(Collection<StagesProect> stagesProectCollection) {
        this.stagesProectCollection = stagesProectCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }
    
}
