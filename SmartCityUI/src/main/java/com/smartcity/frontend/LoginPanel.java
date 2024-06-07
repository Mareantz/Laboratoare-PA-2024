package com.smartcity.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LoginPanel extends JPanel
{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel statusLabel;
    private JFrame frame;

    public LoginPanel(JFrame frame)
    {
        this.frame = frame;
        initializeComponents();
        addComponentsToPanel();
        addListeners();
    }

    private void initializeComponents()
    {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        statusLabel = new JLabel();
    }

    private void addComponentsToPanel()
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(loginButton, gbc);
        gbc.gridx = 1;
        add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(statusLabel, gbc);
    }


    private void addListeners()
    {
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                boolean loginSuccessful = sendLoginRequest(username, password);

                if (loginSuccessful)
                {
                    statusLabel.setText("Login successful!");
                }
                else
                {
                    statusLabel.setText("Invalid credentials");
                }
            }
        });

        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.setContentPane(new RegisterPanel(frame).getPanel());
                frame.revalidate();
            }
        });
    }

    private boolean sendLoginRequest(String username, String password)
    {
        try
        {
            URL url = new URL("http://localhost:8081/api/users/login");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
            String jsonInputString = mapper.writeValueAsString(userLoginDTO);

            try (OutputStream os = connection.getOutputStream())
            {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                try (Scanner scanner = new Scanner(connection.getInputStream()))
                {
                    String responseBody = scanner.useDelimiter("\\A").next();
                    UserDTO userDTO = mapper.readValue(responseBody, UserDTO.class);

                    frame.setContentPane(new UserPanel(userDTO.getId(), userDTO.getUsername()).getPanel());
                    frame.revalidate();

                    return true;
                }
            }
            else
            {
                return false;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public JPanel getPanel()
    {
        return this;
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new LoginPanel(frame));
        frame.pack();
        frame.setVisible(true);
    }

    @Setter
    @Getter
    public static class UserLoginDTO
    {
        private String username;
        private String password;

        public UserLoginDTO()
        {
        }

        public UserLoginDTO(String username, String password)
        {
            this.username = username;
            this.password = password;
        }
    }

    @Setter
    @Getter
    public static class UserDTO
    {
        private Long id;
        private String username;
        private String email;
        private String role;

        public UserDTO()
        {
        }
    }
}