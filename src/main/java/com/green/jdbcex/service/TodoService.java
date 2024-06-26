package com.green.jdbcex.service;

import com.green.jdbcex.dao.TodoDAO;
import com.green.jdbcex.domain.TodoVO;
import com.green.jdbcex.dto.TodoDTO;
import com.green.jdbcex.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {

    INSTANCE;

    private TodoDAO todoDAO;
    private ModelMapper modelMapper;

    TodoService() {

        this.todoDAO = new TodoDAO();
        this.modelMapper=MapperUtil.INSTANCE.get();

    }

    //서비스 계층에서 DTO를 매개변수로 해서 영속 계층으로 등록(연결)
    //글 등록하기
    public void register(TodoDTO todoDTO) throws  Exception{
        TodoVO todoVO= modelMapper.map(todoDTO, TodoVO.class);
//        System.out.println("todoVO: "+ todoVO);
        log.info("todoVO: "+todoVO);
        todoDAO.insert(todoVO);
    }

    // 글 목록 조회
    public List<TodoDTO> listAll() throws Exception{

        List<TodoVO> voList=todoDAO.selectAll();
        log.info(voList);

        //모델 mapper 사용해서 DTO로 변경
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo,TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;



    }

    //글 하나 조회
    public TodoDTO get(Long tno) throws Exception  {
        log.info("tno: " + tno);
        TodoVO todoVO = todoDAO.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    //글 삭제 하기
    public void delete(Long tno)throws Exception{
        log.info("삭제할 글번호(service): "+tno);
        todoDAO.deleteOne(tno);

    }

    //글 수정 하기
    public void modify(TodoDTO todoDTO)throws Exception{
        TodoVO todoVO= modelMapper.map(todoDTO, TodoVO.class);
        log.info("글수정 todoVO: "+todoVO);
        todoDAO.updateOne(todoVO);
    }


}
