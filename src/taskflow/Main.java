package taskflow;

import taskflow.database.DatabaseConnection;
import taskflow.dao.TodoDAO;
import taskflow.view.TodoView;
import taskflow.controller.TodoController;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo cơ sở dữ liệu SQLite trước
        DatabaseConnection.initializeDatabase();

        // Chạy giao diện Swing trên Thread an toàn
        SwingUtilities.invokeLater(() -> {
            TodoDAO dao = new TodoDAO();
            TodoView view = new TodoView();
            new TodoController(dao, view);
            view.setVisible(true);
        });
    }
}
