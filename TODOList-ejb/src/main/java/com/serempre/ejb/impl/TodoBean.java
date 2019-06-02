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

@Stateless
public class TodoBean implements ITodoLocal {

    @PersistenceContext(unitName = "todolistPU")
    protected EntityManager em;

    @Override
    public List<Todo> getTodoALL() {
        return em.createQuery("select t from Todo t", Todo.class).getResultList();
    }

    @Override
    public void save(Todo todo) {
        em.persist(todo);
    }

}
