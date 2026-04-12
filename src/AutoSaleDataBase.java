import java.util.*;

public class AutoSaleDataBase {
    
    private final Map < String, Autos > auto = new HashMap();
    
    public void registerCar ( Autos a)
    {
       auto.put(a.carModel.trim().toLowerCase(), a);
    
    }
    
    public Autos findCar ( String carModel )
    {
        return auto.get(carModel.trim().toLowerCase());
    }
    
    
    public boolean exists( String carModel )
    {
        return auto.containsKey(carModel.trim().toLowerCase());
    }
    
    
    
    
    
    
}
