import com.green.jdbcex.dao.TodoDAO;
import com.green.jdbcex.domain.TodoVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTest {

    private  TodoDAO todoDAO;



//    TodoDAOTest() {
//        todoDAO = new TodoDAO();
//
//    }

    @BeforeEach
    public void ready(){
        todoDAO = new TodoDAO();
    }

    @Test
    void testTime() throws Exception {
        System.out.println("현재시간: " + todoDAO.getTime());
    }


    //교재 코드
    @Test
    public void testInsert() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .title("Sample Title...3")
                .dueDate(LocalDate.of(2021,12,31))
                .build();

        todoDAO.insert(todoVO);
    }

    //전체 글 보기 테스트
    @Test
    void testselectAll() throws Exception{

        List<TodoVO> todoVOList = todoDAO.selectAll();

        todoVOList.stream().forEach((x)->{System.out.println(x);});
    }

    //글 하나 보기 테스트
    @Test
    void testselectOne() throws Exception{
        TodoVO todoVO = todoDAO.selectOne(1L);
        System.out.println(todoVO);

    }

    // 글 삭제 하기 테스트
    @Test
    void testdeleteOne() throws Exception{
        todoDAO.deleteOne(1L);
    }


    //글 수정하기 테스트
    @Test
    public void testUpdateOne() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .tno(1L)
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .finished(true)
                .build();

        todoDAO.updateOne(todoVO);

    }



}
