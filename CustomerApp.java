import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerApp extends JFrame {

    private JTextField idField, lastNameField, firstNameField, phoneField;
    private JButton prevButton, nextButton;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public CustomerApp() {
        // Create the frame
        setTitle("Customer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // ID
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 100, 30);
        add(idLabel);
        idField = new JTextField();
        idField.setBounds(150, 20, 200, 30);
        idField.setEditable(false);
        add(idField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(20, 70, 100, 30);
        add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBounds(150, 70, 200, 30);
        lastNameField.setEditable(false);
        add(lastNameField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(20, 120, 100, 30);
        add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setBounds(150, 120, 200, 30);
        firstNameField.setEditable(false);
        add(firstNameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 170, 100, 30);
        add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(150, 170, 200, 30);
        phoneField.setEditable(false);
        add(phoneField);

        prevButton = new JButton("Previous");
        prevButton.setBounds(50, 220, 100, 30);
        prevButton.addActionListener(e -> showPreviousCustomer());
        add(prevButton);

        nextButton = new JButton("Next");
        nextButton.setBounds(200, 220, 100, 30);
        nextButton.addActionListener(e -> showNextCustomer());
        add(nextButton);

        connectToDatabase();
        loadFirstCustomer();
    }

    private void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase", "username", "password");
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT * FROM Customer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadFirstCustomer() {
        try {
            if (resultSet.first()) {
                displayCustomer();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void displayCustomer() {
        try {
            idField.setText(resultSet.getString("customer_id"));
            lastNameField.setText(resultSet.getString("customer_last_name"));
            firstNameField.setText(resultSet.getString("customer_first_name"));
            phoneField.setText(resultSet.getString("customer_phone"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showPreviousCustomer() {
        try {
            if (resultSet.previous()) {
                displayCustomer();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showNextCustomer() {
        try {
            if (resultSet.next()) {
                displayCustomer();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CustomerApp app = new CustomerApp();
        app.setVisible(true);
    }
}
