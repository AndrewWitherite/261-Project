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
 * @author anw5400
 */
public class User {

    private final String input;            //     C1 C2      
    String[][] accounts = {{"UN1", "PW2"}, //R1   00 01
    {"UN2", "PW1"}};                       //R2   10 11

    public User(String input) {

        this.input = input;
    }

    public int authUserName() {
        int index = -1;

        int length = accounts[0].length;

        for (int i = 0; i < length; i++) {
            if (input.equals(accounts[i][0])) {
                if (input.equals(accounts[0][0]))//"UN1"
                {
                    index = 0;
                }
                if (input.equals(accounts[1][0]))//"UN2"
                {
                    index = 1;
                }

                break;

            } else {
                index = -1;
            }
        }

        return index;
    }

    public int authPassWord() {
        int index = -1;

        int length = accounts[0].length;

        for (int i = 0; i < length; i++) {
            if (input.equals(accounts[i][1])) {
                if (input.equals(accounts[1][1]))//"PW1"
                {
                    index = 0;
                }
                if (input.equals(accounts[0][1]))//"PW2"
                {
                    index = 1;
                }

                break;
            } else {
                index = -1;
            }
        }

        return index;
    }

}
