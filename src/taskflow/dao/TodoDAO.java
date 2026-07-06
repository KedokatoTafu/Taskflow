package taskflow.dao;

import taskflow.database.DatabaseConnection;
import taskflow.model.Todo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    // Lấy danh sách kết hợp tìm kiếm và lọc trạng thái (Đã tối ưu hóa)
    public List<Todo> searchAndFilter(String keyword, String status) {
        List<Todo> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM todos WHERE title LIKE ?");
        
        if (status.equals("Đã hoàn thành")) {
            sql.append(" AND completed = 1");
        } else if (status.equals("Chưa hoàn thành")) {
            sql.append(" AND completed = 0");
        }
        sql.append(" ORDER BY id DESC"); // Công việc mới lên đầu

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Todo(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getInt("completed") == 1,
                    rs.getString("created_at")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm công việc mới
    public boolean add(String title) {
        String sql = "INSERT INTO todos(title) VALUES(?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật tên công việc
    public boolean updateTitle(int id, String newTitle) {
        String sql = "UPDATE todos SET title = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newTitle);
            breakdownId(pstmt, 2, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Đổi trạng thái Hoàn thành / Chưa hoàn thành
    public boolean toggleStatus(int id, boolean currentStatus) {
        String sql = "UPDATE todos SET completed = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, currentStatus ? 0 : 1);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa công việc
    public boolean delete(int id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void breakdownId(PreparedStatement pstmt, int index, int id) throws SQLException {
        pstmt.setInt(index, id);
    }
}
