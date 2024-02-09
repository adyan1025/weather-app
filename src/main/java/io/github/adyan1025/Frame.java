package io.github.adyan1025;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements ActionListener, KeyListener {
    String city = "";
    JTextField response;
    JButton submit;
    JLabel cityLabel;
    JLabel tempLabel;
    Frame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLayout(new FlowLayout());

        cityLabel = new JLabel("Enter the city: ");
        response = new JTextField(20);
        submit = new JButton("Submit");
        submit.addActionListener(this);
        response.addKeyListener(this);
        tempLabel = new JLabel();

        cityLabel.setFont(new Font("Serif", Font.PLAIN, 30));
        tempLabel.setFont(new Font("Serif", Font.PLAIN, 30));

        this.add(cityLabel);
        this.add(response);
        this.add(submit);
        this.add(tempLabel);
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            whenSubmitted();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            whenSubmitted();
        }
    }

    private void whenSubmitted() {
        city = response.getText();
        response.setText("");
    }

    public String getCity() {
        return city;
    }

    public void setTemp(String temp) {
        tempLabel.setText(temp);
    }

    //ignore
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
