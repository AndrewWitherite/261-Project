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

/**
 *
 * @author Andrew Witherite
 */
public class Controller {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ClientViewer.SimpleClient());

        thread1.start();

        System.out.println("Simple Client");

    }

}
