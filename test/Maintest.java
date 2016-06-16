import com.theironyard.Main;
import com.theironyard.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by Erik on 6/15/16.
 */
public class MainTest {

    public Connection startConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test");
        Main.createTable(conn);
        return conn;
    }

    @Test
    public void testUser() throws SQLException {
        Connection conn = startConnection();
        User user1 = new User(1,"Erik", "2728 Waterpointe cir", "wrxerik@gmail.com");
        Main.insertUser(conn, user1);
        ArrayList<User> userList = Main.selectUsers(conn);
        conn.close();

        assertTrue(user1 != null);
    }

    @Test
    public void testUpdate() throws SQLException {
        Connection conn = startConnection();
        User user1 = new User(1,"Erik", "2728 Waterpointe cir", "wrxerik@gmail.com");
        Main.insertUser(conn, user1);
        User user2 = new User(user1.getId(), "Stephen", "2818 longwood dr", "srs12188@yahoo.com");
        Main.updateUser(conn, user2);
        ArrayList<User> userList = Main.selectUsers(conn);
        conn.close();

        assertTrue(user1 != null);
    }

    @Test
    public void testDelete() throws SQLException {
        Connection conn = startConnection();
        User user1 = new User(1, "Erik", "2728 Waterpointe cir", "wrxerik@gmail.com");
        User user2 = new User(2, "Stephen", "2818 longwood dr", "srs12188@yahoo.com");
        Main.insertUser(conn, user1);
        Main.insertUser(conn, user2);
        Main.deleteUser(conn, 1);
        ArrayList<User> userList = Main.selectUsers(conn);
        conn.close();

        assertTrue(user1 == null);
        assertTrue(user2 == null);
    }
}

