/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
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
@Table(name = "typetask")
public class Typetask implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_type_task")
    @SequenceGenerator(sequenceName = "customer_type_task", allocationSize = 1, name = "customer_type_task")
    @NotNull
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "typetask")
    private Collection<Tasktab> tasktabCollection;

    public Typetask() {
    }

    public Typetask(Long id) {
        this.id = id;
    }

    public Typetask(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Typetask(String inputName, String exampleTextarea, Optional<Typetask> findById, Date dateVipoln, Date date, Optional<Statetask> findById0, Optional<AppUser> findById1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        if (!(object instanceof Typetask)) {
            return false;
        }
        Typetask other = (Typetask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.oktpp.tuskoktpp.entity.Typetask[ id=" + id + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
