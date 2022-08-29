package g56133.atl.stib.model.dto;

import javafx.util.*;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StopDto extends Dto<Pair<Integer, Integer>>{
    
    private int order;
    private String name;
    
    public StopDto(Integer line, Integer station, int order) {
        super(new Pair<>(line, station));
        this.order = order;
        this.name = null;
    }
    
    public StopDto(Integer line, Integer station, int order, String name) {
        super(new Pair<>(line, station));
        this.order = order;
        this.name = name;
    }
    
    public int getOrder() {
        return this.order;
    }
    
    public String getName() {
        return this.name;
    }
}
