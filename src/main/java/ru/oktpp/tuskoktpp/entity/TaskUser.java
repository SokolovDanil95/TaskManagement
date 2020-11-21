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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danil
 */
@Entity
@Table(name = "task_user")
public class TaskUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_task_user")
    @SequenceGenerator(sequenceName = "customer_task_user", allocationSize = 1, name = "customer_task_user")
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "iduser", referencedColumnName = "user_id")
    @ManyToOne
    private AppUser iduser;
    @JoinColumn(name = "idtask", referencedColumnName = "id")
    @ManyToOne
    private Tasktab idtask;

    public TaskUser() {
    }

    public TaskUser(Long id) {
        this.id = id;
    }
    
    public TaskUser(AppUser iduser, Tasktab idtask) {
        this.idtask = idtask;
        this.iduser = iduser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getIduser() {
        return iduser;
    }

    public void setIduser(AppUser iduser) {
        this.iduser = iduser;
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
        if (!(object instanceof TaskUser)) {
            return false;
        }
        TaskUser other = (TaskUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.oktpp.tuskoktpp.entity.TaskUser[ id=" + id + " ]";
    }
    
}
