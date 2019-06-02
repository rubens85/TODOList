/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rubens
 */
@Entity
@Table(name = "collaborator")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Collaborator.findAll", query = "SELECT c FROM Collaborator c"),
    @NamedQuery(name = "Collaborator.findById", query = "SELECT c FROM Collaborator c WHERE c.id = :id"),
    @NamedQuery(name = "Collaborator.findByName", query = "SELECT c FROM Collaborator c WHERE c.name = :name"),
    @NamedQuery(name = "Collaborator.findByLastName", query = "SELECT c FROM Collaborator c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Collaborator.findByIdentificationCard", query = "SELECT c FROM Collaborator c WHERE c.identificationCard = :identificationCard")})
public class Collaborator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;
    @Size(max = 2147483647)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 2147483647)
    @Column(name = "identification_card")
    private String identificationCard;
    @OneToMany(mappedBy = "collaborator", fetch = FetchType.EAGER)
    private Set<Todo> todoSet;

    public Collaborator() {
    }

    public Collaborator(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }

    public String getFullName() {
        return this.name+ " "+this.lastName;
    }
    
    public void resetFields() {
        this.id = null;
        this.identificationCard = "";
        this.lastName = "";
        this.name = "";
        this.todoSet = null;
    }

    @XmlTransient
    public Set<Todo> getTodoSet() {
        return todoSet;
    }

    public void setTodoSet(Set<Todo> todoSet) {
        this.todoSet = todoSet;
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
        if (!(object instanceof Collaborator)) {
            return false;
        }
        Collaborator other = (Collaborator) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.serempre.entity.Collaborator[ id=" + id + " ]";
    }
    
}
