package com.ohgiraffers.datajpa.section03.pagingandsort;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagingAndSortService {

    private PagingAndSortRepository pagingAndSortRepository;

    public PagingAndSortService(PagingAndSortRepository pagingAndSortRepository) {
        this.pagingAndSortRepository = pagingAndSortRepository;
    }

    public Page<FoundMenuResponseDTO> findAllMenusWithPaging(int offset, int limit) {

        return pagingAndSortRepository.findAll(PageRequest.of(offset, limit))
                .map(FoundMenuResponseDTO::new);
    }

    public List<FoundMenuResponseDTO> findAllMenusOrderBy(String columnName) {

        return pagingAndSortRepository.findAll(Sort.by(columnName).descending())
                .stream()
                .map(FoundMenuResponseDTO::new)
                .toList();
    }
}
