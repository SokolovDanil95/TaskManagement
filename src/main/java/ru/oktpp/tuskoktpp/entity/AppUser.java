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
@Table(name = "app_user")

public class AppUser implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "encryted_password")
    private String encrytedPassword;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enabled")
    private int enabled;
    @OneToMany(mappedBy = "iduser")
    private Collection<UsersProect> usersProectCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_appuser")
    @SequenceGenerator(sequenceName = "customer_appuser", allocationSize = 1, name = "customer_appuser")
    @Column(name = "user_id")
    private Long userId;
    @OneToMany(mappedBy = "directortask")
    private Collection<Tasktab> tasktabCollection;

    public AppUser() {
    }

    public AppUser(Long userId) {
        this.userId = userId;
    }

    public AppUser(Long userId, String userName, String encrytedPassword, int enabled) {
        this.userId = userId;
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
        this.enabled = enabled;
    }
    
    public AppUser(String userName, String encrytedPassword, int enabled) {
        this.userName = userName;
        this.encrytedPassword = encrytedPassword;
        this.enabled = enabled;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ru.oktpp.tuskoktpp.entity.AppUser_1[ userId=" + userId + " ]";
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @XmlTransient
    public Collection<UsersProect> getUsersProectCollection() {
        return usersProectCollection;
    }

    public void setUsersProectCollection(Collection<UsersProect> usersProectCollection) {
        this.usersProectCollection = usersProectCollection;
    }
    
}
