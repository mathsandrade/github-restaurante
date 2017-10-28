
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;





import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Matheus
 */
public class Pool {
    private static Connection[] connections = new Connection[5];
    private static boolean[] usedConnections = new boolean[5];

    static {

        for (int i = 0; i < 5; i++) {
            try {
                connections[i] = BuildConnection.pg();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection get(){
        while(true) {
            for (int i = 0; i < 5; i++) {
                if(usedConnections[i] == false) {
                    usedConnections[i] = true;
                    return connections[i];
                }
            }
        }
    }

    public static void release(Connection conn) {
        for (int i = 0; i < 5; i++) {
            if (connections[i] == conn) {
                usedConnections[i] = false;
            }
        }
    }
}
