package com.ohgiraffers.section01.autowired.common;

import org.springframework.context.annotation.Bean;

import java.util.List;

public interface BookDAO {
    List<BookDTO> findAllBooks();

    BookDTO findBookBySeq(int seq);
}
