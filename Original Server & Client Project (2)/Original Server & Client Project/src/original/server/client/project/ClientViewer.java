/*
 * TO RUN THIS APPLICATION:
 * 1. Right click the Server.java file from the project directory, then click 'Run File'
 *    This will start up the server, which will sit and wait for a client to connect to it before it does anything else
 * 2. Right click the Controller.java file from the project directory, then click 'Run File' on that as well
 *    This will create a client that looks to connect to a server with the same socket, which should already be running from Step 1.
 * 
 * In the console, you can see that there are 2 builds running (the server and client), click through them to see full funcionality.
 * 
 * THERE ARE 2 USERS FOR THIS:
 * User 1: username = UN1   -   password = PW1
 * User 2: username = UN2   -   password = PW2
 * 
 * To test this project, run the application like mentioned above. Next enter one of the two designated user's information. The program will pause for 10 seconds at the begining to sync sessions.
 * After that, you can enter the designated information.
 */
package original.server.client.project;

import java.util.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import static java.lang.System.out;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author anw5400
 */
public class ClientViewer {

    static class SimpleClient extends JFrame implements Runnable {

        public JLabel userLabel;
        public JTextField userText;
        public JLabel passwordLabel;
        public JPasswordField passwordText;
        public JButton nextButton;
        public JButton submitButton;
        public static String user = "User";
        public static String pass = "Password";
        public static String next = "Next";
        public static String submit = "Submit";

        public SimpleClient() {
            super("Login");
            setLayout(null);

            userLabel = new JLabel(user);

            userLabel.setBounds(10, 20, 80, 25);
            add(userLabel);

            //userText = new JTextField("UN1");
            userText = new JTextField(20);

            userText.setBounds(100, 20, 165, 25);
            add(userText);

            /*passwordLabel = new JLabel(pass);
            passwordLabel.setBounds(10, 50, 80, 25);
            add(passwordLabel);
            passwordLabel.setVisible(false);*/

            /*JPasswordField passwordText = new JPasswordField(20);
            passwordText.setBounds(100, 50, 165, 25);
            add(passwordText);
            passwordText.setVisible(false);*/

            nextButton = new JButton(next);
            nextButton.setBounds(10, 80, 80, 25);
            getContentPane().add(nextButton);

            thehandler handler = new thehandler();
            userText.addActionListener(handler);
            nextButton.addActionListener(handler);
            //thehandler2 handler2 = new thehandler2();
            //passwordText.addActionListener(handler2);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(350, 200);
            setVisible(true);

        }

        private class thehandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent event){
                    synchronized (Server.class)  {

                     
                    //notify();
                //String user = userText.getText();
                //String pass = new String(passwordText.getPassword());

                //System.out.println(user);
                
                //if ("UN1".equals(userText.getText()) || "UN2".equals(userText.getText()) ) {
                    passwordLabel = new JLabel(pass);
                    passwordLabel.setBounds(10, 50, 80, 25);
                    add(passwordLabel).setFocusable(true);

                    JPasswordField passwordText = new JPasswordField(20);
                    passwordText.setBounds(100, 50, 165, 25);
                    add(passwordText).setFocusable(true);
                                        
                    thehandler2 handler2 = new thehandler2();
                    
                    passwordText.addActionListener(handler2);
                    
                    
                    submitButton = new JButton(submit);
                    submitButton.setBounds(10, 80, 80, 25);
                    getContentPane().add(submitButton);
                    nextButton.setVisible(false);
                    submitButton.setVisible(true);
                    submitButton.addActionListener(handler2);
                    validate();
                    repaint();
                    
                    
                    //notify();
                    
                    try{
                        Thread.sleep(10000);
                    }
                    catch(InterruptedException ex)
                    {
                        Thread.currentThread().interrupt();
                    }
                                        
                    
                    //submitButton.addActionListener(handler2);
                    
                    //setVisible(false);
                    //JOptionPane.showMessageDialog(null, "nice");
                //} else {
                  //JOptionPane.showMessageDialog(null, "bad");
                //}
            }
        }

        }
        
        private class thehandler2 implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent event)
            {
                synchronized (Server.class)  {
                if("PW1".equals(passwordText.getText()) || "PW2".equals(passwordText.getText()))
                {
                    //notify();
                    
                    JOptionPane.showMessageDialog(null, "Logged In");
                }
                else{
                    
                  JOptionPane.showMessageDialog(null, "Login Failed");

                }
                }
            }
        }

        @Override
        public void run() {
            synchronized (Server.class) 
            {
                
                try{
                    Thread.sleep(10000);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }
                
                try {
                    System.out.println("Waiting for connection.....");
                    InetAddress localAddress = InetAddress.getLocalHost();

                    try (Socket clientSocket = new Socket(localAddress, 6000);
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                        //SimpleClient frame1 = new SimpleClient();

                        System.out.println("Connected to server");
                        Scanner scanner = new Scanner(System.in);
                        while (true) {

                            System.out.println("Enter Username: ");
                            
                            
                            String username = userText.getText();

                            //String username = scanner.nextLine();
                            System.out.println(username);
                            out.println(username);
                            String responses = br.readLine();

                            System.out.println(responses);

                            if (responses.equals("Username does not exist")) {
                                setVisible(false);
                                break;
                            }
                            
                            /*try{
                               Thread.sleep(10000);
                            }
                            catch(InterruptedException ex)
                            {
                                Thread.currentThread().interrupt();
                            }*/
                            
                                       
                            System.out.println("Enter Password: ");
                            //String password = scanner.nextLine();                            
                            
                            String password = passwordText.getText();
                            
                            
                            out.println(password);
                            
                            String response = br.readLine();

                            System.out.println(response);

                            if (response.equals("Logged in")) {
                                //setVisible(false);
                                break;
                            }
                            if (response.equals("Password does not match Username")) {
                                //setVisible(false);
                                break;
                            }

                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
