package g56133.atl.Mentoring.dto;

public class Dto <T>{
    
    private T key;
    
    public Dto(T key) {
        this.key = key;
    }
    
    public T getKey() {
        return this.key;
    }
    
    public int hashcode() {
        return this.hashCode();
    }
    
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        
        System.out.println(o instanceof Integer);
        if (!(o instanceof Integer)) {
            return false;
        }

        Dto tmpKey = (Dto) o;
        System.out.println("o1 " + this.key +" o2 " + tmpKey);
        System.out.println(this.key == tmpKey);
        return this.key == tmpKey;
    }
}
