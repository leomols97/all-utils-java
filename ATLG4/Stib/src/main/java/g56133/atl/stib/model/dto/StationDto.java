package g56133.atl.stib.model.dto;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StationDto extends Dto<Integer>{
    
    private String name;
    
    public StationDto(Integer key, String name) {
        super(key);
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    } 
}
