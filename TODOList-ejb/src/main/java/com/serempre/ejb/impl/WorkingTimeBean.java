/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.ejb.impl;

import com.serempre.ejb.inter.local.ITodoLocal;
import com.serempre.entity.Todo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;
import org.tempuri.Calculator;
import com.serempre.ejb.inter.local.IWorkingTimeLocal;
import com.serempre.entity.WorkingTime;
import javax.persistence.TypedQuery;

@Stateless
public class WorkingTimeBean implements IWorkingTimeLocal {

    @PersistenceContext(unitName = "todolistPU")
    protected EntityManager em;
    
    @WebServiceRef(wsdlLocation = "http://www.dneonline.com/calculator.asmx?WSDL")
    public Calculator calculator;

    @Override
    public void save(WorkingTime wt) {
        em.persist(wt);
    }

    @Override
    public List<WorkingTime> find(Todo todo) {
        TypedQuery<WorkingTime> query = em.createQuery("SELECT wt FROM WorkingTime wt WHERE wt.todo = :todo", WorkingTime.class);
        query.setParameter("todo", todo);
        
        return query.getResultList();
    }
    

}
