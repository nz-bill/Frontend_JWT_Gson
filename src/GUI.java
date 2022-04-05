import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    ConnectionManager connectionManager;

    JLabel nameLabel;
    JLabel responseLabel;

    String savedToken;

    public GUI(){
        connectionManager = new ConnectionManager();

        JTextField nameField = new JTextField();
        JTextField passwordField = new JTextField();


        nameLabel = new JLabel("Welcome!");
        responseLabel = new JLabel("enter two numers");

        JButton loginButton = new JButton("Press me!");
        JButton requestButton = new JButton("Hello there!");

        nameField.setBounds(80, 100, 200, 50);
        passwordField.setBounds(300, 100, 200, 50);

        loginButton.setBounds(250,170,100,50);
        requestButton.setBounds(250,500,200,50);

        nameLabel.setBounds(50, 300, 500,50);
        responseLabel.setBounds(100,400,200,50);

        add(nameLabel);
        add(loginButton);
        add(responseLabel);
        add(nameField);
        add(passwordField);
        add(requestButton);


        setSize(600,600);
        setLayout(null);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(()->{

                   String reslutString = connectionManager
                           .sendRequest("authorize?name="+ nameField.getText()+"&mail="+passwordField.getText());
                    savedToken = reslutString;
                    nameLabel.setText(reslutString);
                }).start();
            }
        });
        requestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(()->{

                    String reslutString = connectionManager
                            .sendRequestWithToken("hello", savedToken);

                    responseLabel.setText(reslutString);
                }).start();
            }
        });
    }

    public void parseCustomer(String customerAsString){
        Gson gson = new Gson();

        Customer customer = gson.fromJson(customerAsString, Customer.class);
        nameLabel.setText(customer.getName());
        responseLabel.setText(customer.getEmail());
    }

    public  void parseCustomerAsJsonObject(String customerAsString){
        Gson gson = new Gson();

        JsonObject customer = gson.fromJson(customerAsString, JsonObject.class);
        nameLabel.setText(customer.get("name").getAsString());
        responseLabel.setText(customer.get("email").getAsString());

    }
}
//  parseCustomerAsJsonObject(reslutString);
//                 String resultString = connectionManager.sendRequest("admin");