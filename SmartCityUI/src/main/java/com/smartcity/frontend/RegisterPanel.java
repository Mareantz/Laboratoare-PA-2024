package com.smartcity.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RegisterPanel {
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton backButton;
    private JFrame parentFrame;

    public RegisterPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        initializeComponents();
        addComponentsToPanel();
        addListeners();
    }

    private void initializeComponents() {
        panel = new JPanel(new GridBagLayout());
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        emailField = new JTextField(20);
        registerButton = new JButton("Register");
        backButton = new JButton("Back to Login");
    }

    private void addComponentsToPanel() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);
        gbc.gridy = 3;
        panel.add(registerButton, gbc);
        gbc.gridx = 0;
        panel.add(backButton, gbc);
    }

    private void addListeners() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    String email = emailField.getText();
                    register(username, password, email);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Registration failed");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new LoginPanel(parentFrame).getPanel());
                parentFrame.validate();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    private void register(String username, String password, String email) throws IOException {
        String url = "http://localhost:8081/api/users/register";
        ObjectMapper mapper = new ObjectMapper();
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO(username, password, email);
        String json = mapper.writeValueAsString(userRegistrationDTO);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(json));

            try (CloseableHttpResponse response = client.execute(post)) {
                String responseString = EntityUtils.toString(response.getEntity());
                if (responseString.contains("User registered successfully")) {
                    JOptionPane.showMessageDialog(panel, "Registration successful");
                    parentFrame.setContentPane(new LoginPanel(parentFrame).getPanel());
                    parentFrame.validate();
                } else {
                    JOptionPane.showMessageDialog(panel, "Registration failed");
                }
            }
        }
    }

    static class UserRegistrationDTO {
        private String username;
        private String password;
        private String email;

        public UserRegistrationDTO() {}

        public UserRegistrationDTO(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
