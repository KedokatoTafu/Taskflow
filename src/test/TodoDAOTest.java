package test;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import taskflow.dao.TodoDAO;
import taskflow.model.Todo;

public class TodoDAOTest {
    private TodoDAO dao = new TodoDAO();
    
    @Test
    public void testAddTodo() {
        // Giả sử đưa vào một công việc mới, hàm add() phải trả về true
        boolean result = dao.add("Test công việc tự động");
        assertTrue(result);
    }

    @Test
    public void testToggleStatus() {
        // Giả sử có một công việc ID = 1 đang "Chưa hoàn thành" (currentStatus = false)
        // Việc toggle (đổi trạng thái) phải trả về true
        boolean result = dao.toggleStatus(1, false);
        assertTrue("Đổi trạng thái thất bại", result);
    }

    @Test
    public void testDeleteTodo() {
        // Thêm một công việc ảo vào trước
        dao.add("Công việc chuẩn bị xóa");
        
        // Cần lấy ID của công việc vừa thêm để xóa (giả sử là ID lớn nhất trong DB)
        List<Todo> list = dao.searchAndFilter("Công việc chuẩn bị xóa", "Tất cả");
        if (!list.isEmpty()) {
            int targetId = list.get(0).getId();
            boolean deleteResult = dao.delete(targetId);
            assertTrue("Xóa công việc thất bại", deleteResult);
        }
    }

    @Test
    public void testSearchFunction() {
        dao.add("Học Unit Test");
        List<Todo> result = dao.searchAndFilter("Học Unit Test", "Tất cả");
        
        assertNotNull("Danh sách trả về bị null", result);
        assertTrue("Không tìm thấy công việc vừa thêm", result.size() > 0);
    }
    
    @Test
    public void testUpdateNonExistentTodo() {
        // Cố tình truyền vào một ID không hề tồn tại trong Database (VD: ID = 99999)
        // Kết quả kỳ vọng: Hàm updateTitle phải trả về false (không cập nhật được)
        boolean result = dao.updateTitle(99999, "Tên mới");
        assertFalse("Lỗi: Hệ thống báo cập nhật thành công cho một ID không tồn tại!", result);
    }

    @Test
    public void testDeleteNonExistentTodo() {
        // Cố tình xóa một công việc không tồn tại
        // Kết quả kỳ vọng: Hàm delete phải trả về false
        boolean result = dao.delete(-1); // ID âm chắc chắn không tồn tại
        assertFalse("Lỗi: Hệ thống báo xóa thành công một ID ảo!", result);
    }
}
