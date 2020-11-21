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
@Table(name = "proect")
public class Proect implements Serializable {

    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "idproect")
    private Collection<UsersProect> usersProectCollection;
    @OneToMany(mappedBy = "idproect")
    private Collection<StagesProect> stagesProectCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_proect")
    @SequenceGenerator(sequenceName = "customer_proect", allocationSize = 1, name = "customer_proect")
    @Column(name = "id")
    private Long id;
    @Column(name = "daterun")
    @Temporal(TemporalType.TIMESTAMP)
    private Date daterun;
    @Column(name = "dateend")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateend;
    @Column(name = "readiness")
    private Integer readiness;
    @JoinColumn(name = "idsost", referencedColumnName = "id")
    @ManyToOne
    private Statetask idsost;

    public Proect() {
    }
    
    public Proect(String name, String description, Date daterun, Date dateend,
        Statetask idsost, Integer readiness) {
        this.name = name;
        this.description = description;
        this.daterun = daterun;
        this.dateend = dateend;
        this.idsost = idsost;
        this.readiness = readiness;
    }

    public Proect(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getDaterun() {
        return daterun;
    }

    public void setDaterun(Date daterun) {
        this.daterun = daterun;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    public Integer getReadiness() {
        return readiness;
    }

    public void setReadiness(Integer readiness) {
        this.readiness = readiness;
    }

    public Statetask getIdsost() {
        return idsost;
    }

    public void setIdsost(Statetask idsost) {
        this.idsost = idsost;
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
        if (!(object instanceof Proect)) {
            return false;
        }
        Proect other = (Proect) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.oktpp.tuskoktpp.entity.Proect[ id=" + id + " ]";
    }


    @XmlTransient
    public Collection<StagesProect> getStagesProectCollection() {
        return stagesProectCollection;
    }

    public void setStagesProectCollection(Collection<StagesProect> stagesProectCollection) {
        this.stagesProectCollection = stagesProectCollection;
    }


    @XmlTransient
    public Collection<UsersProect> getUsersProectCollection() {
        return usersProectCollection;
    }

    public void setUsersProectCollection(Collection<UsersProect> usersProectCollection) {
        this.usersProectCollection = usersProectCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
