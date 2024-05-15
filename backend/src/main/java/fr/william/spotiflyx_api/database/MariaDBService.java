package fr.william.spotiflyx_api.database;

import org.bson.Document;

import java.sql.*;

public class MariaDBService {
    private static Connection connection;

    public static void connect() {
        try {
            String jdbcUrl = System.getenv("JDBC_URL");
            String jdbcUser = System.getenv("JDBC_USER");
            String jdbcPassword = System.getenv("JDBC_PASSWORD");

            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            System.out.println("Connected to the MariaDB server successfully.");

            // Check if the 'users' table exists, if not, create it
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM information_schema.tables WHERE table_name = 'users'");
            if (!rs.next()) {
                stmt.executeUpdate("CREATE TABLE users (id SERIAL PRIMARY KEY, email VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, first_name VARCHAR(255) NOT NULL, last_name VARCHAR(255) NOT NULL, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, data JSON)");
                System.out.println("Table users created successfully.");
            }
            rs = stmt.executeQuery("SELECT * FROM information_schema.tables WHERE table_name = 'content'");
            if (!rs.next()) {
                stmt.executeUpdate("CREATE TABLE content (id SERIAL PRIMARY KEY, api_id VARCHAR(255) NOT NULL, title VARCHAR(255) NOT NULL, artist VARCHAR(255) NOT NULL, image_url VARCHAR(255) NOT NULL, likedBy JSON, contentType VARCHAR(255) NOT NULL)");
                System.out.println("Table content created successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Connection to the MariaDB server failed.");
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection to the PostgreSQL server closed successfully.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public boolean emailExists(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(String email, String hashedPassword, String firstName, String lastName) {
        String sql = "INSERT INTO users (email, password, first_name, last_name, data) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, firstName);
            stmt.setString(4, lastName);
            stmt.setString(5, "{}");
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPassword(String id) throws Exception {
        String sql = "SELECT password FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            } else {
                throw new RuntimeException("Id not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPasswordFromMail(String mail) {
        String sql = "SELECT password FROM users WHERE email = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, mail);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            } else {
                throw new RuntimeException("Email not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePassword(String id, String hashedNewPassword) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, hashedNewPassword);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String email) {
        String sql = "DELETE FROM users WHERE email = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean idExists(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserData getUserData(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserData(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        Document.parse(rs.getString("data"))
                );
            } else {
                throw new RuntimeException("Id not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserData(String token, String email, String firstName, String lastName, Document data) {
        String sql = "UPDATE users SET email = ?, first_name = ?, last_name = ?, data = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, data.toJson());
            stmt.setInt(5, Integer.parseInt(token));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(String newEmail, String hashedNewPassword, String newFirstName, String newLastName) {
        String sql = "UPDATE users SET email = ?, password = ?, first_name = ?, last_name = ? WHERE email = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, newEmail);
            stmt.setString(2, hashedNewPassword);
            stmt.setString(3, newFirstName);
            stmt.setString(4, newLastName);
            stmt.setString(5, newEmail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createContent(String api_id, String title, String artist, String image_url, ContentType contentType) {
        String sql = "INSERT INTO content (api_id, title, artist, image_url, likedBy, contentType) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, api_id);
            stmt.setString(2, title);
            stmt.setString(3, artist);
            stmt.setString(4, image_url);
            stmt.setString(5, "{}");
            stmt.setString(6, contentType.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean contentExists(String id) {
        String sql = "SELECT * FROM content WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ContentData getContentData(String id) {
        String sql = "SELECT * FROM content WHERE api_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ContentData(
                        rs.getInt("id"),
                        rs.getString("api_id"),
                        rs.getString("title"),
                        rs.getString("artist"),
                        rs.getString("image_url"),
                        Document.parse(rs.getString("likedBy")),
                        ContentType.valueOf(rs.getString("contentType"))
                );
            } else {
                throw new RuntimeException("Id not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateContentData(String id, String api_id, String title, String artist, String image_url, Document likedBy, ContentType contentType) {
        String sql = "UPDATE content SET api_id = ?, title = ?, artist = ?, image_url = ?, likedBy = ?, contentType = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, api_id);
            stmt.setString(2, title);
            stmt.setString(3, artist);
            stmt.setString(4, image_url);
            stmt.setString(5, likedBy.toJson());
            stmt.setString(6, contentType.toString());
            stmt.setInt(7, Integer.parseInt(id));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteContent(String id) {
        String sql = "DELETE FROM content WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
