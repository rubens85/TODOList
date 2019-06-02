/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rubens
 */
@Entity
@Table(name = "working_time")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkingTime.findAll", query = "SELECT w FROM WorkingTime w"),
    @NamedQuery(name = "WorkingTime.findById", query = "SELECT w FROM WorkingTime w WHERE w.id = :id"),
    @NamedQuery(name = "WorkingTime.findByTimeWorked", query = "SELECT w FROM WorkingTime w WHERE w.timeWorked = :timeWorked")})
public class WorkingTime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "time_worked")
    private Float timeWorked;
    @JoinColumn(name = "id_todo", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Todo todo;

    public WorkingTime() {
    }

    public WorkingTime(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(Float timeWorked) {
        this.timeWorked = timeWorked;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
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
        if (!(object instanceof WorkingTime)) {
            return false;
        }
        WorkingTime other = (WorkingTime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.serempre.entity.WorkingTime[ id=" + id + " ]";
    }
    
}
