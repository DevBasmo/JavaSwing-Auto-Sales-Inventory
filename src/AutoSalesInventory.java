
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;
import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class AutoSalesInventory extends JFrame{
    
 
    private JComboBox<String> carMakeBox, carColors,transmissionType,
            fuelType,engineType;
    private JTextField carModelTextField,vinNumberField,searchCarField;
    private JSpinner yearSpinner, millageSpinner;
    private JFormattedTextField currencyField;
    private JCheckBox availableButton,soldButton;
    private JDateChooser dateChooser;
    private String dateStringg;
    private JTextArea carDescriptionArea;
    private JButton addButton, searchButton;
    private JTable display;
    private DefaultTableModel model;
    private NumberFormat currencyFormat;
    
    private AutoSaleDataBase db = new AutoSaleDataBase();

    
    public AutoSalesInventory()
    {
        super ("Auto Sale Inventory System");
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(900, 700));
        
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        wrapper.setLayout(new BorderLayout());
        wrapper.add(scrollPane, BorderLayout.NORTH);
        add(wrapper, BorderLayout.NORTH);
        
        
        // Sales Icon
        ImageIcon icon = new ImageIcon(getClass().getResource("AutoSales.jpg"));
        Image img = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(img);
        JLabel iconLabel = new JLabel(scaledIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.weightx = 1;
      
        panel.add(iconLabel, gbc);
        
        
        //Headies
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        JLabel title = new JLabel("Auto Sale Inventory System");
        title.setFont(new Font("SanSerif", Font.BOLD, 50));
        title.setForeground(Color.BLUE);
        title.setOpaque(true);
        title.setBackground(Color.LIGHT_GRAY);
        panel.add(title, gbc);
        
        
        //Search car
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel searchCarLabel = new JLabel("Search Car:");
        searchCarLabel.setFont(new Font("Arial", Font.BOLD, 16));
        searchCarLabel.setForeground(Color.DARK_GRAY);
        panel.add(searchCarLabel, gbc);
        
        
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchCarField = new JTextField(35);
        searchCarField.setForeground(Color.DARK_GRAY);
        panel.add(searchCarField, gbc);
        
        
        //Car Make
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel carMake = new JLabel ("Car Make:");
        carMake.setFont(new Font("Arial", Font.BOLD, 16));
        carMake.setForeground(Color.DARK_GRAY);
        panel.add(carMake, gbc);
        
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String []carMakes = {"Select Car Make","Toyota","Honda","BMW","Mercedes",
            "Kia","Hyundai","Ford","Lexus"
        };
        carMakeBox =new JComboBox<>(carMakes);
        carMakeBox.setFont(new Font("Arial", Font.PLAIN, 14));
        carMakeBox.setBackground(Color.WHITE);
        panel.add(carMakeBox, gbc);
        
        //Car Model
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel carModelLabel = new JLabel("Car  Model:");
        carModelLabel.setFont(new Font("Arial", Font.BOLD, 16));
        carModelLabel.setForeground(Color.DARK_GRAY);
        panel.add(carModelLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        carModelTextField = new JTextField(35);
        carModelTextField.setForeground(Color.GRAY);
        panel.add(carModelTextField, gbc);
        
        //Car Year
        gbc.gridx= 0;
        gbc.gridy ++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel carYearLabel = new JLabel("Car Year:");
        carYearLabel.setFont( new Font ("Arial", Font.BOLD, 16));
        carYearLabel.setForeground(Color.DARK_GRAY);
        panel.add(carYearLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        SpinnerNumberModel yearModel = new SpinnerNumberModel(2025, 1990, 2035, 1);
        yearSpinner = new JSpinner(yearModel);
        JSpinner.NumberEditor editor =new JSpinner.NumberEditor(yearSpinner, "#");
        yearSpinner.setEditor(editor);
        yearSpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(yearSpinner, gbc);
        
        //Car Color
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel carColorLabel = new JLabel("Car Color:");
        carColorLabel.setFont(new Font("Arial", Font.BOLD, 16));
        carColorLabel.setForeground(Color.DARK_GRAY);
        panel.add(carColorLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        String [] colors = {"Select Car Color","White", "Black", "Silver", "Gray",
            "Blue","Red", "Green", "Brown/Brown", "Gold", "Yellow",
            "Orange"
        };
        carColors = new JComboBox<>(colors);
        carColors.setFont( new Font("Arial", Font.PLAIN, 14));
        carColors.setBackground(Color.WHITE);
        panel.add(carColors, gbc);
        
        
        //Car Millage
        gbc.gridx = 0;
        gbc.gridy ++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel carMillage = new JLabel ("Car Millage:");
        carMillage.setFont(new Font("Arial", Font.BOLD, 16));
        carMillage.setForeground(Color.DARK_GRAY);
        panel.add(carMillage, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        SpinnerNumberModel millage = new SpinnerNumberModel(0, 0, 500000, 1000);
        millageSpinner = new JSpinner(millage);
        JSpinner.NumberEditor editorr = new JSpinner.NumberEditor(millageSpinner, "#");
        millageSpinner.setEditor(editorr);
        millageSpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(millageSpinner, gbc);
        
        
        //Car Price
        gbc.gridx =0;
        gbc.gridy++;
        gbc.fill= GridBagConstraints.HORIZONTAL;
        JLabel carPriceLabel = new JLabel("Car Price:");
        carPriceLabel.setFont( new Font("Arial", Font.BOLD, 16));
        carPriceLabel.setForeground(Color.DARK_GRAY);
        panel.add(carPriceLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Locale nigeria = new Locale.Builder().setLanguage("en").setRegion("NG").build();
        currencyFormat = NumberFormat.getCurrencyInstance(nigeria);
        
        currencyField = new JFormattedTextField(currencyFormat);
        currencyField.setValue(0.00);
        currencyField.setColumns(10);
        panel.add(currencyField, gbc);
        
        
        //CarStatus
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel carStatusLabel = new JLabel ("Car Status:");
        carStatusLabel.setFont(new Font ("Arial", Font.BOLD, 16));
        carStatusLabel.setForeground(Color.DARK_GRAY);
        panel.add(carStatusLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill =  GridBagConstraints.HORIZONTAL;
        availableButton = new JCheckBox("Available");
        soldButton = new JCheckBox("Sold");
        availableButton.setBackground(Color.GRAY);
        soldButton.setBackground(Color.GRAY);
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        statusPanel.add(soldButton);
        statusPanel.add(availableButton);
        panel.add(statusPanel, gbc);
        
        soldButton.addActionListener(e->{
            if (soldButton.isSelected())
            {
                availableButton.setSelected(false);
            }
        
    });
        
        availableButton.addActionListener(e->{
            if (availableButton.isSelected())
            {
                soldButton.setSelected(false);
            }
        }
        );
        
        
        //Date added
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel dateAdded = new JLabel ("Date Added:");
        dateAdded.setFont(new Font("Arial", Font.BOLD, 16));
        dateAdded.setForeground(Color.DARK_GRAY);
        panel.add(dateAdded, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        panel.add(dateChooser, gbc);
        
        
        //VIN Number
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel vinNumberLabel = new JLabel("Vehicle Id Number:");
        vinNumberLabel.setFont(new Font("Arial", Font.BOLD, 16));
        vinNumberLabel.setForeground(Color.DARK_GRAY);
        panel.add(vinNumberLabel, gbc);
        
        
        gbc.gridx = 1;
        gbc.fill= GridBagConstraints.HORIZONTAL;
        vinNumberField = new JTextField(35);
        vinNumberField.setForeground(Color.GRAY);
        panel.add(vinNumberField, gbc);
        
        
        //Transmission Type
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel transmissionLabel = new JLabel("Transmission Type:");
        transmissionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        transmissionLabel.setForeground(Color.DARK_GRAY);
        panel.add(transmissionLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] transmission = {"Select Transmission","Automatic","Manual"};
        transmissionType =  new JComboBox<>(transmission);
        transmissionType.setFont(new Font("Arial", Font.PLAIN, 14));
        transmissionType.setBackground(Color.WHITE);
        panel.add(transmissionType, gbc);
        
        
        //Fuel Type
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel fuelTypeLabel = new JLabel("Fuel Type:");
        fuelTypeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        fuelTypeLabel.setForeground(Color.DARK_GRAY);
        panel.add(fuelTypeLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] fuelTypes = {"Select fuel types", "Petrol", "Diesel",
            "Hybrid","Electric"};
        fuelType = new JComboBox<>(fuelTypes);
        fuelType.setFont(new Font("Arial", Font.PLAIN, 14));
        fuelType.setBackground(Color.WHITE);
        panel.add(fuelType, gbc);
        
        //Engine Type
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel engineTypeLabel = new JLabel("Engine Type:");
        engineTypeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        engineTypeLabel.setForeground(Color.DARK_GRAY);
        panel.add(engineTypeLabel, gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] engineTypes = {"Select engine type","1.4L","1.8L","2.0L",
            "2.0L","2.4L","2.5L","3.0L","3.5L","4.0L","4.6","5.0"};
        
        engineType = new JComboBox <> (engineTypes);
        engineType.setFont(new Font("Arial", Font.BOLD, 14));
        engineType.setBackground(Color.WHITE);
        panel.add(engineType, gbc);
        
        
        //Car description
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel descriptionLabel = new JLabel("Car Description:");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        descriptionLabel.setForeground(Color.DARK_GRAY);
        panel.add(descriptionLabel, gbc);
        
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        carDescriptionArea = new JTextArea(5, 5);
        carDescriptionArea.setLineWrap(true);
        carDescriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionscrollPanel = new JScrollPane(carDescriptionArea);
        descriptionscrollPanel.setPreferredSize(new Dimension(300, 80));
        panel.add(descriptionscrollPanel, gbc);
        
        
        //Add Button
        gbc.gridx = 1;
        gbc.gridy++;
        JPanel buttonPanel = new JPanel( new FlowLayout(FlowLayout.LEFT, 10, 0));
        addButton = new JButton("Add Car");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.RED);
        buttonPanel.add(addButton);
        panel.add(buttonPanel, gbc);
        
        //Search Button
        gbc.gridx = 2;
        searchButton = new JButton("Search Button");
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.RED);
        buttonPanel.add(searchButton);
        
        
        //Car Display
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel carsOnDisplay = new JLabel("Cars  On Display:");
        carsOnDisplay.setFont(new Font("Arial", Font.BOLD, 16));
        carsOnDisplay.setForeground(Color.DARK_GRAY);
        panel.add(carsOnDisplay, gbc);
        
        
        String[]columnsList = {"Car Make","Car Model","Car Year",
            "Car Color","Car Millage","Car Price", "Car Status",
            "Date Added","Vehicle Id Number","Transmission Type",
            "Fuel Type", "Engine Type", "Car Descrition"
        };
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        model = new DefaultTableModel(columnsList, 0)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        
        JTable carDisplayTable = new JTable(model);
        
        carDisplayTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
       carDisplayTable.getColumnModel().getColumn(12).setCellRenderer(new TextAreaRenderer());
      carDisplayTable.getColumnModel().getColumn(0).setPreferredWidth(100);
      carDisplayTable.getColumnModel().getColumn(1).setPreferredWidth(200);
      carDisplayTable.getColumnModel().getColumn(2).setPreferredWidth(100);
      carDisplayTable.getColumnModel().getColumn(3).setPreferredWidth(100);
      carDisplayTable.getColumnModel().getColumn(4).setPreferredWidth(100);
      carDisplayTable.getColumnModel().getColumn(5).setPreferredWidth(100);
      carDisplayTable.getColumnModel().getColumn(6).setPreferredWidth(100);
      carDisplayTable.getColumnModel().getColumn(7).setPreferredWidth(100);
      carDisplayTable.getColumnModel().getColumn(8).setPreferredWidth(150);
      carDisplayTable.getColumnModel().getColumn(9).setPreferredWidth(150);
      carDisplayTable.getColumnModel().getColumn(10).setPreferredWidth(100);
      carDisplayTable.getColumnModel().getColumn(11).setPreferredWidth(100);
      carDisplayTable.getColumnModel().getColumn(12).setPreferredWidth(200);
      
        JScrollPane displayPane = new JScrollPane(carDisplayTable);
        displayPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel.add(displayPane, gbc);
        
        
        
        
        
       addButton.addActionListener(handler -> registerVehicle());
       
       searchButton.addActionListener(handler->searchVehicle());
        
        
        
        
    
        
        
        
 
                
        
        
       
        
        
        
        
    }
    
    private void searchVehicle()
    {

        String searchCarModel = searchCarField.getText().trim();
        Autos autos = db.findCar(searchCarModel);
        
        if (autos == null)
        {
            JOptionPane.showMessageDialog(this, "This Vehicle is not found!");
            searchCarField.setText("");
            return;
        }
        
        carModelTextField.setText(autos.carModel);
        carMakeBox.setSelectedItem(autos.carMaker);
        yearSpinner.setValue(Integer.valueOf(autos.carYear));
        carColors.setSelectedItem(autos.carColor);
        millageSpinner.setValue(Integer.valueOf(autos.millage));
        try{
            Locale nigeiraLocale = new Locale("en", "NG");
            String originalPrice = autos.carPrice;
            String cleanPriceString = originalPrice.replaceAll("[^\\d.]", "");
            double price = Double.parseDouble(cleanPriceString);
            currencyField.setValue(price);
            
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "Invalid price format: "+autos.carPrice);
            currencyField.setValue(0.00);
        }
        if (autos.carStatus.equalsIgnoreCase("Available"))
        {
            availableButton.setSelected(true);
            soldButton.setSelected(false);
        }
        else if(autos.carStatus.equalsIgnoreCase("Sold"))
        {
            soldButton.setSelected(true);
            availableButton.setSelected(false);
            
        }
        try{
            if(autos.dateStringgg != null && !autos.dateStringgg.isEmpty()) 
                    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(autos.dateStringgg);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dateChooser.setCalendar(cal);
                    }
            else
            {
                JOptionPane.showMessageDialog(this, "Date is missing");
            }
        }
        catch(ParseException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid date format!");
        }
        vinNumberField.setText(autos.vehicleIdNumber);
        transmissionType.setSelectedItem(autos.transmission);
        fuelType.setSelectedItem(autos.carFuelType);
        engineType.setSelectedItem(autos.carEngineType);
        carDescriptionArea.setText(autos.carDescription);
        
        
        
    }
  
    private void registerVehicle()
    {
        String carModel = carModelTextField.getText().trim();
        if (db.exists(carModel))
        {
            JOptionPane.showMessageDialog(this, "Vehicle already exist!");
            carMakeBox.setSelectedIndex(0);
            carModelTextField.setText("");
            yearSpinner.setValue(0);
            carColors.setSelectedIndex(0);
            millageSpinner.setValue(0);
            currencyField.setValue(0.00);
            availableButton.setSelected(false);
            soldButton.setSelected(false);
            dateChooser.setDate(null);
            vinNumberField.setText("");
            transmissionType.setSelectedIndex(0);
            fuelType.setSelectedIndex(0);
            engineType.setSelectedIndex(0);
            carDescriptionArea.setText("");
           
            return;
                    }; 
        
        String carMaker = (String) carMakeBox.getSelectedItem();
       
        String carYear  = yearSpinner.getValue().toString();
        String carColor = (String) carColors.getSelectedItem();
        String millage = millageSpinner.getValue().toString();
        Number value = (Number) currencyField.getValue();
        String carPrice = currencyFormat.format(value);
        String carStatus= availableButton.isSelected() ? "Available" : soldButton.isSelected() ? "Sold" : "Not selected";
        Date selectedDate = dateChooser.getDate();
            if (selectedDate  == null)
            {
                JOptionPane.showMessageDialog(null, "Please select a date before proceeding.");
                return;
            }
            SimpleDateFormat dDate = new SimpleDateFormat("yyyy-MM-dd");
        String dateStringgg = dDate.format(selectedDate);
        String vehicleIdNumber = vinNumberField.getText().trim();
        String transmission = (String) transmissionType.getSelectedItem();
        String carFuelType = (String) fuelType.getSelectedItem();
        String carEngineType = (String) engineType.getSelectedItem();
        String carDescription = carDescriptionArea.getText().trim();
        
        
        Autos autos  = new Autos (carMaker,carModel,carYear,carColor,
                millage,carPrice,carStatus,dateStringgg,vehicleIdNumber,
                transmission,carFuelType,carEngineType,carDescription );
        db.registerCar(autos);
        JOptionPane.showMessageDialog(this , "This vehicle registered successfully.");
         carMakeBox.setSelectedIndex(0);
            carModelTextField.setText("");
            yearSpinner.setValue(0);
            carColors.setSelectedIndex(0);
            millageSpinner.setValue(0);
            currencyField.setValue(0.00);
            availableButton.setSelected(false);
            soldButton.setSelected(false);
            dateChooser.setDate(null);
            vinNumberField.setText("");
            transmissionType.setSelectedIndex(0);
            fuelType.setSelectedIndex(0);
            engineType.setSelectedIndex(0);
            carDescriptionArea.setText("");
            
       
        
            
            String[] rowData = 
            {
                
               carMaker,carModel,carYear,carColor,millage,carPrice,
                carStatus,dateStringgg,vehicleIdNumber,transmission,
                carFuelType,carEngineType,carDescription
                
            };
            model.addRow(rowData);

        
        
        
    }
    
    
    
    static  class TextAreaRenderer extends JTextArea implements TableCellRenderer
    {
        public TextAreaRenderer()
        {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }
        
        
    
    
        @Override
    public Component getTableCellRendererComponent (JTable productTable, Object value,
            boolean isSelected, boolean hasFocus, int row,  int column)
            {
                setText(value != null ? value.toString() : "");
                setSize(productTable.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
                int preferredHeight = getPreferredSize().height;
                
                if ( productTable.getRowHeight(row) != preferredHeight)
                {
                    productTable.setRowHeight(row, preferredHeight);
                }
                
                if(isSelected)
                {
                    setBackground(productTable.getSelectionBackground());
                    setForeground(productTable.getSelectionForeground());
                }
                else
                {
                    setBackground(productTable.getBackground());
                    setForeground(productTable.getForeground());
                }
               
               return this;
          
       
       
            }
    }
}
