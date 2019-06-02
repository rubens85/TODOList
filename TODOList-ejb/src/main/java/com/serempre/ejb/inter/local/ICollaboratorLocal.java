/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.ejb.inter.local;

import com.serempre.entity.Collaborator;
import com.serempre.handleexception.UserNotFoundException;
import javax.ejb.Local;

/**
 *
 * @author rubens
 */
@Local
public interface ICollaboratorLocal {
    
    public Collaborator findByIdentification(String identification) throws UserNotFoundException;
}
