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

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 *
 * @author anw5400
 */
public class Server {

    static class SimpleServer implements Runnable {

        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(6000)) {
                System.out.println("Waiting for connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to client");

                try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String username;
                    String password;
                    
                   
                    
                    while ((username = br.readLine()) != null) {
                        User checkUser = new User(username);

                        if (checkUser.authUserName() == 0 || checkUser.authUserName() == 1) {
                            out.println("Good Username");
                            
                            //JOptionPane.showMessageDialog(null, "Good Username");

                            int check;

                            check = checkUser.authUserName();

                            while ((password = br.readLine()) != null) {
                                User checkPass = new User(password);

                                if (checkPass.authPassWord() == check) {
                                    out.println("Logged in");
                                    
                                    JOptionPane.showMessageDialog(null, "Logged in");
                                    
                                    break;

                                }
                                if (checkPass.authPassWord() == -1 || checkUser.authUserName() != checkPass.authPassWord()) {
                                    out.println("Password does not match Username");
                                    
                                    break;
                                }
                            }

                        }
                        if (checkUser.authUserName() == -1) {
                            System.out.println("1: " + username);
                            out.println("Username does not exist");
                            
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

    public static void main(String[] args) {
        System.out.println("Simple Server");

        Thread thread1 = new Thread(new SimpleServer());

        thread1.start();
    }

}
