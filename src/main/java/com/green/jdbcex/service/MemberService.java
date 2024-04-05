package com.green.jdbcex.service;

import com.green.jdbcex.dao.MemberDAO;
import com.green.jdbcex.domain.MemberVO;
import com.green.jdbcex.dto.MemberDTO;
import com.green.jdbcex.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

@Log4j2
public enum MemberService {
    INSTANCE;
    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService(){
        this.dao = new MemberDAO();
        this.modelMapper = MapperUtil.INSTANCE.get();
    }

    public MemberDTO login(String mid, String mpw) throws Exception {

        MemberVO vo=null;
        try{
            vo =dao.getWithPassword(mid, mpw);
        }catch (Exception e){
            log.info("멤버서비스 로그인 예외발생");
        }


        MemberDTO memberDTO=modelMapper.map(vo, MemberDTO.class);
        return  memberDTO;
    }


}
