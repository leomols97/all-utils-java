/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56133.mentoring.repository;

/**
 *
 * @author Leong Paeg-Hing
 */
public class RepositoryException extends Exception {
    
    public RepositoryException(String message) {
        super(message);
    }
    
    public RepositoryException(Exception e) {
        super(e.getMessage());
    }
}
