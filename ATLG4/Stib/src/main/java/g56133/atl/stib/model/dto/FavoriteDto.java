/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56133.atl.stib.model.dto;

/**
 *
 * @author Leong Paeg-Hing
 */
public class FavoriteDto extends Dto<String>{
    
    private int origin;
    private int destintion;
    
    public FavoriteDto(String name, int origin, int destination) {
        super(name);
        this.origin = origin;
        this.destintion = destination;
    }

    public int getOrigin() {
        return origin;
    }

    public int getDestintion() {
        return destintion;
    }
}
