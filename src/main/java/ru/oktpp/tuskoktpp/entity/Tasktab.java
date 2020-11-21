/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "tasktab")

public class Tasktab implements Serializable {

    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "evaluation")
    private Double evaluation;
    @OneToMany(mappedBy = "idtask")
    private Collection<LinkStageProect> linkStageProectCollection;
    @OneToMany(mappedBy = "idtask")
    private Collection<ComentTask> comentTaskCollection;
    @JoinColumn(name = "typetask", referencedColumnName = "id")
    @ManyToOne
    private Typetask typetask;
    @Column(name = "modeldate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modeldate;
    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne
    private Statetask state;
    @OneToMany(mappedBy = "idtask")
    private Collection<TaskUser> taskUserCollection;
    @JoinColumn(name = "directortask", referencedColumnName = "user_id")
    @ManyToOne
    private AppUser directortask;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_tasktab")
    @SequenceGenerator(sequenceName = "customer_tasktab", allocationSize = 1, name = "customer_tasktab")
    @Column(name = "id")
    private Long id;

    public Tasktab() {
    }

    public Tasktab(Long id) {
        this.id = id;
    }
    
    public Tasktab(String name, String description, Typetask typetask, Date modeldate, Date startdate, Statetask state, AppUser directortask) {
        this.name = name;
        this.description = description;
        this.typetask = typetask;
        this.modeldate = modeldate;
        this.startdate = startdate;
        this.state = state;
        this.directortask = directortask;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Tasktab)) {
            return false;
        }
        Tasktab other = (Tasktab) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.oktpp.tuskoktpp.entity.Tasktab[ id=" + id + " ]";
    }


    public AppUser getDirectortask() {
        return directortask;
    }

    public void setDirectortask(AppUser directortask) {
        this.directortask = directortask;
    }


    @XmlTransient
    public Collection<TaskUser> getTaskUserCollection() {
        return taskUserCollection;
    }

    public void setTaskUserCollection(Collection<TaskUser> taskUserCollection) {
        this.taskUserCollection = taskUserCollection;
    }


    public Statetask getState() {
        return state;
    }

    public void setState(Statetask state) {
        this.state = state;
    }


    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }


    public Date getModeldate() {
        return modeldate;
    }

    public void setModeldate(Date modeldate) {
        this.modeldate = modeldate;
    }


    public Typetask getTypetask() {
        return typetask;
    }

    public void setTypetask(Typetask typetask) {
        this.typetask = typetask;
    }


    @XmlTransient
    public Collection<ComentTask> getComentTaskCollection() {
        return comentTaskCollection;
    }

    public void setComentTaskCollection(Collection<ComentTask> comentTaskCollection) {
        this.comentTaskCollection = comentTaskCollection;
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

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }

    @XmlTransient
    public Collection<LinkStageProect> getLinkStageProectCollection() {
        return linkStageProectCollection;
    }

    public void setLinkStageProectCollection(Collection<LinkStageProect> linkStageProectCollection) {
        this.linkStageProectCollection = linkStageProectCollection;
    }
    
}
