import javax.swing.JFrame;
public class AutoSalesMainClass {
    
    public static void main (String [] args)
    {
        AutoSalesInventory Basmo = new AutoSalesInventory();
        Basmo.setVisible(true);
        Basmo.setSize(880, 580);
        Basmo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
