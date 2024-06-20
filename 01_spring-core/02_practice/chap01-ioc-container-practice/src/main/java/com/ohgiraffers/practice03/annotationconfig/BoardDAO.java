package com.ohgiraffers.practice03.annotationconfig;

import com.ohgiraffers.common.BoardDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BoardDAO {
    private final Map<Long, BoardDTO> boardMap;

    public BoardDAO() {
        boardMap = new HashMap<>();

        boardMap.put(1L, new BoardDTO(1L, "스프링 수업 어떠세요", "저는 새롭고 재밌네요", "개발자 꿈나무"));
        boardMap.put(2L, new BoardDTO(2L,"스프링 수업이 앞으로 많이 어려워 질까요?","그래도 포기하지 않고 열심히 해볼거예요!","미래의 멋진 개발자"));
    }

    public BoardDTO selectBoard(Long id){
        return boardMap.get(id);
    }

    public boolean insertBoard(BoardDTO boardDTO){
        int before = boardMap.size();

        boardMap.put(boardDTO.getId(), boardDTO);

        int after = boardMap.size();

        return after > before;
    }
}
