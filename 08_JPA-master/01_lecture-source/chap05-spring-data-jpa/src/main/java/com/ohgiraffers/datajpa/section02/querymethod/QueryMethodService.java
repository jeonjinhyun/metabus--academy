package com.ohgiraffers.datajpa.section02.querymethod;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryMethodService {

    private QueryMethodRepository queryMethodRepository;

    public QueryMethodService(QueryMethodRepository queryMethodRepository) {
        this.queryMethodRepository = queryMethodRepository;
    }

    public List<FoundMenuResponseDTO> findByMenuPriceGreaterThan(int menuPrice) {

        return queryMethodRepository.findByMenuPriceGreaterThan(menuPrice)
                .stream()
                .map(FoundMenuResponseDTO::new)
                .toList();
    }

    public List<FoundMenuResponseDTO> findByMenuNameContaining(String menuName) {

        return queryMethodRepository.findByMenuNameContaining(menuName)
                .stream()
                .map(FoundMenuResponseDTO::new)
                .toList();
    }
}
