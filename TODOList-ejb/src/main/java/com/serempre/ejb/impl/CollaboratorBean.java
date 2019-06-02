/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.ejb.impl;

import com.serempre.ejb.inter.local.ICollaboratorLocal;
import com.serempre.entity.Collaborator;
import com.serempre.handleexception.UserNotFoundException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author rubens
 */
@Stateless
public class CollaboratorBean implements ICollaboratorLocal {

    @PersistenceContext(unitName = "todolistPU")
    protected EntityManager em;

    @Override
    public Collaborator findByIdentification(String identification) throws UserNotFoundException {
        try {
            TypedQuery<Collaborator> query = em.createQuery("from Collaborator c where c.identificationCard = :idCard", Collaborator.class);
            query.setParameter("idCard", identification);
            return query.getSingleResult();
        } catch (NoResultException nre) {
            throw new UserNotFoundException("User not found");
        }
    }
    
    public void metodo() throws UserNotFoundException {
        
    }

}
