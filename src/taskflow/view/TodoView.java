package taskflow.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TodoView extends JFrame {
    private JTextField txtTitle, txtSearch;
    private JButton btnAdd, btnEdit, btnDelete, btnToggle, btnSearch;
    private JComboBox<String> cbFilter;
    private JTable table;
    private DefaultTableModel tableModel;

    public TodoView() {
        setTitle("TaskFlow - Ứng dụng Quản lý Công việc");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Thanh điều khiển phía trên (Thêm công việc)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createTitledBorder("Thêm công việc mới"));
        txtTitle = new JTextField(35);
        btnAdd = new JButton("Thêm mới");
        topPanel.add(new JLabel("Tên công việc: "));
        topPanel.add(txtTitle);
        topPanel.add(btnAdd);

        // 2. Thanh công cụ ở giữa (Tìm kiếm và Lọc)
        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Tìm kiếm");
        cbFilter = new JComboBox<>(new String[]{"Tất cả", "Chưa hoàn thành", "Đã hoàn thành"});
        toolPanel.add(new JLabel("Tìm: "));
        toolPanel.add(txtSearch);
        toolPanel.add(btnSearch);
        toolPanel.add(new JLabel("  Lọc trạng thái: "));
        toolPanel.add(cbFilter);

        // 3. Bảng hiển thị danh sách (Center)
        tableModel = new DefaultTableModel(new Object[]{"ID", "Tên công việc", "Trạng thái", "Ngày tạo"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } // Không cho edit trực tiếp trên cell
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.add(toolPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // 4. Thanh chức năng phía dưới (Sửa, Xóa, Trạng thái)
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnToggle = new JButton("Đổi trạng thái (V)");
        btnEdit = new JButton("Sửa tên");
        btnDelete = new JButton("Xóa bỏ");
        
        // Colors
        btnToggle.setBackground(new Color(46, 204, 113)); btnToggle.setForeground(Color.WHITE);
        btnEdit.setBackground(new Color(52, 152, 219)); btnEdit.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(231, 76, 60)); btnDelete.setForeground(Color.WHITE);

        bottomPanel.add(btnToggle);
        bottomPanel.add(btnEdit);
        bottomPanel.add(btnDelete);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public String getTodoTitle() { return txtTitle.getText().trim(); }
    public void clearTodoTitle() { txtTitle.setText(""); }
    public String getSearchKeyword() { return txtSearch.getText().trim(); }
    public String getFilterStatus() { return cbFilter.getSelectedItem().toString(); }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnToggle() { return btnToggle; }
    public JButton getBtnSearch() { return btnSearch; }
    public JComboBox<String> getCbFilter() { return cbFilter; }
}
