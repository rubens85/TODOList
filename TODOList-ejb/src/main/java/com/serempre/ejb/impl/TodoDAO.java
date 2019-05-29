/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.ejb.impl;

import com.serempre.ejb.inter.local.ITodoLocal;
import com.serempre.entity.Todo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TodoDAO implements ITodoLocal {

    @PersistenceContext(unitName="todolistPU") 
    protected static EntityManager em;
    
    @Override
    public List<Todo> getTodoALL() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
