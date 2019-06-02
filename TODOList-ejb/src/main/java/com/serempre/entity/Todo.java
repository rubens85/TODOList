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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rubens
 */
@Entity
@Table(name = "todo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Todo.findAll", query = "SELECT t FROM Todo t"),
    @NamedQuery(name = "Todo.findById", query = "SELECT t FROM Todo t WHERE t.id = :id"),
    @NamedQuery(name = "Todo.findByTitle", query = "SELECT t FROM Todo t WHERE t.title = :title"),
    @NamedQuery(name = "Todo.findByDescription", query = "SELECT t FROM Todo t WHERE t.description = :description"),
    @NamedQuery(name = "Todo.findByEstimatedTime", query = "SELECT t FROM Todo t WHERE t.estimatedTime = :estimatedTime"),
    @NamedQuery(name = "Todo.findByTimeRemaining", query = "SELECT t FROM Todo t WHERE t.timeRemaining = :timeRemaining"),
    @NamedQuery(name = "Todo.findByTotalTimeWorked", query = "SELECT t FROM Todo t WHERE t.totalTimeWorked = :totalTimeWorked")})
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "title")
    private String title;
    @NotNull
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Column(name = "estimated_time")
    private Short estimatedTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "time_remaining")
    private Float timeRemaining;
    @Column(name = "total_time_worked")
    private Float totalTimeWorked;
    @JoinColumn(name = "id_collaborator", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Collaborator collaborator;
    @OneToMany(mappedBy = "todo", fetch = FetchType.EAGER)
    private Set<WorkingTime> workingTimeSet;

    public Todo() {
    }

    public Todo(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public Todo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        if(description == null)
            return description;
        return description.length()>16?description.substring(0, 16)+"...":description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Short estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Float getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(Float timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public Float getTotalTimeWorked() {
        return totalTimeWorked;
    }

    public void setTotalTimeWorked(Float totalTimeWorked) {
        this.totalTimeWorked = totalTimeWorked;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    @XmlTransient
    public Set<WorkingTime> getWorkingTimeSet() {
        return workingTimeSet;
    }

    public void setWorkingTimeSet(Set<WorkingTime> workingTimeSet) {
        this.workingTimeSet = workingTimeSet;
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
        if (!(object instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.serempre.entity.Todo[ id=" + id + " ]";
    }
    
}
