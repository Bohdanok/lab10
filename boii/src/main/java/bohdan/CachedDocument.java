package bohdan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CachedDocument extends DocumentDecorator {
    private static final String DB_URL = "jdbc:sqlite:/home/julfy1/Documents/OOP/lab10/boii/src/main/java/bohdan/db.sqlite";

    public CachedDocument(Document document) {
        super(document);
    }

    @Override
    public String parse(String path) {
        String cachedResult = getCachedParsedString(path);

        // Return cached result if available
        if (cachedResult != null) {
            return cachedResult;
        }

        // Otherwise, parse and cache the result
        String result = super.parse(path);
        cacheParsedString(path, result);
        return result;
    }

    private String getCachedParsedString(String path) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String query = "SELECT parsed_string FROM files WHERE path = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, path);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("parsed_string");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void cacheParsedString(String path, String parsedString) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String insert = "INSERT INTO files (path, parsed_string) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insert)) {
                pstmt.setString(1, path);
                pstmt.setString(2, parsedString);
                pstmt.executeUpdate();
                System.out.println("Cache insert successful for path: " + path);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
