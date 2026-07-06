package taskflow.controller;

import taskflow.dao.TodoDAO;
import taskflow.model.Todo;
import taskflow.view.TodoView;
import javax.swing.*;
import java.util.List;

public class TodoController {
    private TodoDAO todoDAO;
    private TodoView todoView;

    public TodoController(TodoDAO todoDAO, TodoView todoView) {
        this.todoDAO = todoDAO;
        this.todoView = todoView;

        this.todoView.getBtnAdd().addActionListener(e -> handleAdd());
        this.todoView.getBtnToggle().addActionListener(e -> handleToggle());
        this.todoView.getBtnEdit().addActionListener(e -> handleEdit());
        this.todoView.getBtnDelete().addActionListener(e -> handleDelete());
        this.todoView.getBtnSearch().addActionListener(e -> refreshData());
        this.todoView.getCbFilter().addActionListener(e -> refreshData());

        refreshData();
    }

    // Làm mới dữ liệu hiển thị trên bảng
    private void refreshData() {
        String keyword = todoView.getSearchKeyword();
        String status = todoView.getFilterStatus();
        List<Todo> list = todoDAO.searchAndFilter(keyword, status);

        todoView.getTableModel().setRowCount(0); // Xóa dữ liệu cũ trên bảng
        for (Todo todo : list) {
            todoView.getTableModel().addRow(new Object[]{
                todo.getId(),
                todo.getTitle(),
                todo.isCompleted() ? "Đã hoàn thành" : "Chưa hoàn thành",
                todo.getCreatedAt()
            });
        }
    }

    // Xử lý thêm mới công việc
    private void handleAdd() {
        String title = todoView.getTodoTitle();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(todoView, "Tên công việc không được để trống!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (todoDAO.add(title)) {
            todoView.clearTodoTitle();
            refreshData();
        }
    }

    // Xử lý đổi trạng thái công việc
    private void handleToggle() {
        int selectedRow = todoView.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(todoView, "Vui lòng chọn một công việc để đổi trạng thái!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int id = (int) todoView.getTableModel().getValueAt(selectedRow, 0);
        String statusStr = (String) todoView.getTableModel().getValueAt(selectedRow, 2);
        boolean currentStatus = statusStr.equals("Đã hoàn thành");

        if (todoDAO.toggleStatus(id, currentStatus)) {
            refreshData();
        }
    }

    // Xử lý chỉnh sửa tên công việc
    private void handleEdit() {
        int selectedRow = todoView.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(todoView, "Vui lòng chọn công việc cần sửa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int id = (int) todoView.getTableModel().getValueAt(selectedRow, 0);
        String oldTitle = (String) todoView.getTableModel().getValueAt(selectedRow, 1);

        String newTitle = JOptionPane.showInputDialog(todoView, "Nhập tên mới cho công việc:", oldTitle);
        if (newTitle != null && !newTitle.trim().isEmpty()) {
            if (todoDAO.updateTitle(id, newTitle.trim())) {
                refreshData();
            }
        } else if (newTitle != null) {
            JOptionPane.showMessageDialog(todoView, "Tên công việc không được bỏ trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Xử lý xóa công việc
    private void handleDelete() {
        int selectedRow = todoView.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(todoView, "Vui lòng chọn công việc cần xóa!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int id = (int) todoView.getTableModel().getValueAt(selectedRow, 0);
        String title = (String) todoView.getTableModel().getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(todoView, 
                "Bạn có chắc chắn muốn xóa công việc: \"" + title + "\"? ", 
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                
        if (confirm == JOptionPane.YES_OPTION) {
            if (todoDAO.delete(id)) {
                refreshData();
            }
        }
    }
}
