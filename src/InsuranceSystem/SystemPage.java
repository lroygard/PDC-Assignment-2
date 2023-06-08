package InsuranceSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SystemPage extends JFrame implements SystemPageObserver {

    public static final int CURRENTYEAR = 2023;

    private HomePage homePage;
    private JPanel currentPanel;

    //Components on background
    private JLabel logo;
    private JButton backButton;
    private JLabel customerLabel;
    private JLabel customerInfo;
    private JLabel userInfo;
    private JLabel user;
    private JLabel managerInfo;

    //To set bounds
    private int x;
    private int y;
    private int width;
    private int height;

    //Initialise fonts
    private final Font FONT = new Font("Calibri", Font.BOLD, 15);
    private final Font FONTPLAIN = new Font("Calibri", Font.PLAIN, 15);

    //Holds the signle instance of SystemPage
    private static SystemPage instance;

    //Private constructor to restrict instantation from other classes
    private SystemPage() {
        LoginPage lip = new LoginPage(this);
        lip.setVisible(true);
    }

    ;
    
    /**
     * Singleton pattern method to get the instance of SystemPage
     * @return the system page instance
     */
    public static SystemPage getInstance() {
        if (instance == null) {
            instance = new SystemPage();
        }
        return instance;
    }

    /**
     * Override method to handle the notification of correct password
     *
     * @param loggedInStaff the staff that has logged in
     */
    @Override
    public void notifyPasswordCorrect(Staff loggedInStaff) {
        InsuranceSystem.getInstance().currentStaff = loggedInStaff;
        createPage();
    }

    /**
     * Creates the main page of the system, setting up the user interface and
     * initializing its components. This method creates the HomePage, logo,
     * staff visual, back button, customer visual, and sets the window
     * properties. It also makes the window visible.
     */
    private void createPage() {
        //Create a new homepage
        homePage = new HomePage();

        //initalise components
        createLogo();
        createStaffVisual();
        createBackButton();
        createCustomerVisual();

        //Adds components to the screen
        addToPanel();

        //Handle formatting of the frame
        setTitle("Insurance System Tool");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //Show the homepage
        showPanel(homePage.getPanel());

        //Make the window visible
        setVisible(true);
    }

    /**
     * Creates, resizes and adds the logo.
     */
    private void createLogo() {
        //Initalise JLabel
        logo = new JLabel();
        logo.setBounds(0, 10, 250, 175);

        //Get Logo and resive it
        ImageIcon originalIcon = new ImageIcon("./resources/logo.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(225, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        //Add image to label
        logo.setIcon(resizedIcon);
    }

    /**
     * Creates the back button to allow the user to go back to the homepage at
     * any time. Adds the action listener.
     */
    private void createBackButton() {
        //Create button
        backButton = createButton("Show HomePage", Color.WHITE, 390, 55, 200, 50);
        add(backButton);

        //When back button is pressed, restore home page
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPanel(homePage.getPanel());
            }
        });
    }

    /**
     * Shows the currently logged in staff member on the screen. Also shows
     * whether they have manager permissions.
     */
    private void createStaffVisual() {
        //Set Bounds
        x = 700;
        y = 40;
        width = 200;
        height = 15;

        //Create Labels
        userInfo = createLabel("Logged in as: ", FONT, x, y += 20, width, height);
        user = createLabel(InsuranceSystem.getInstance().currentStaff.getFullName(), FONTPLAIN, x + width - 100, y, width, height);
        managerInfo = createLabel("Manager Permissions: " + Boolean.toString(InsuranceSystem.getInstance().currentStaff.isManager()).toUpperCase(), FONT, x, y + 20, width, height);
    }

    /**
     * Adds the components to the screen.
     */
    private void addToPanel() {
        add(logo);
        add(user);
        add(userInfo);
        add(managerInfo);
        add(customerLabel);
        add(customerInfo);
    }

    /**
     * Shows the current customer on the screen.
     */
    private void createCustomerVisual() {
        //Set bounds
        x = 700;
        y = 115;
        width = 200;
        height = 15;

        //Create labels
        customerLabel = createLabel("Current Customer Selected: ", FONT, x, y, width, height);
        customerInfo = createLabel("", FONTPLAIN, x + 2, y + 15, width, height);

        //Get customer
        Customer customer = InsuranceSystem.getInstance().currentCustomer;

        //Add customer to visual
        updateCustomerVisual(customer);
    }

    /**
     * Updates the current customer on the screen.
     *
     * @param customer the customer to be shown.
     */
    public void updateCustomerVisual(Customer customer) {
        //If no customer is selected
        if (customer == null) {
            customerInfo.setText("None Selected");
        } else {
            //If a customer is selected
            int id = customer.getId();
            String fullName = customer.getFullName();

            customerInfo.setText(id + ": " + fullName);
        }
        //Repaint to show updated customer
        repaint();
    }

    /**
     * Shows a panel on the screen
     *
     * @param panel the panel to be shown
     */
    public void showPanel(JPanel panel) {
        //If there is a panel, remove it
        if (currentPanel != null) {
            remove(currentPanel);
        }

        //Change the current panel to the inputted pane;
        this.currentPanel = panel;

        //Add the current panel to the screen
        add(currentPanel);

        //Repaint to show updated panel
        validate();
        repaint();
    }

    /**
     * Creates a JButton
     *
     * @param text The text on the button
     * @param x The x-coord of the button
     * @param y The y-coord of the button
     * @param width The width of the button
     * @param height The heigh of the button
     * @return the button that was created
     */
    public static JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(224, 224, 224));
        return button;
    }

    /**
     * Creates a JButton
     *
     * @param text The text on the button
     * @param colour The colour of the button
     * @param x The x-coord of the button
     * @param y The y-coord of the button
     * @param width The width of the button
     * @param height The heigh of the button
     * @return the button that was created
     */
    public static JButton createButton(String text, Color colour, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(colour);
        return button;
    }

    /**
     * Creates a JTextField
     *
     * @param text The text in the text field
     * @param x The x-coord of the text field
     * @param y The y-coord of the text field
     * @param width The width of the text field
     * @param height The heigh of the text field
     * @return The text field that was created
     */
    public static JTextField createTextField(String text, int x, int y, int width, int height) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, width, height);
        return textField;
    }

    /**
     * Creates a JComboBox
     *
     * @param x The x-coord of the combo box
     * @param y The y-coord of the combo box
     * @param width The width of the combo box
     * @param height The heigh of the combo box
     * @return The combo box that was created
     */
    public static JComboBox createComboBox(int x, int y, int width, int height) {
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(x, y, width, height);
        return comboBox;
    }

    /**
     * Creates a JLabel
     *
     * @param text The text on the label
     * @param x The x-coord of the label
     * @param y The y-coord of the label
     * @param width The width of the label
     * @param height The heigh of the label
     * @return the label that was created
     */
    public static JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }

    /**
     * Creates a JLabel
     *
     * @param text The text on the label
     * @param font The font on the label
     * @param x The x-coord of the label
     * @param y The y-coord of the label
     * @param width The width of the label
     * @param height The heigh of the label
     * @return the label that was created
     */
    public static JLabel createLabel(String text, Font font, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        return label;
    }

    /**
     * Creates a JLabel
     *
     * @param text The text on the label
     * @param font The font on the label
     * @param colour the colour of the font on the label
     * @param x The x-coord of the label
     * @param y The y-coord of the label
     * @param width The width of the label
     * @param height The heigh of the label
     * @return the label that was created
     */
    public static JLabel createLabel(String text, Font font, Color colour, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setForeground(colour);
        label.setBounds(x, y, width, height);
        label.setFont(font);
        return label;
    }

    /**
     * Creates a JLabel
     *
     * @param text The text on the label
     * @param colour the colour of the font on the label
     * @param x The x-coord of the label
     * @param y The y-coord of the label
     * @param width The width of the label
     * @param height The heigh of the label
     * @return the label that was created
     */
    public static JLabel createLabel(String text, Color colour, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setForeground(colour);
        label.setBounds(x, y, width, height);
        return label;
    }
}
