package com.ohgiraffers.section02.annotaion.subsection01.primary;

import com.ohgiraffers.section02.annotaion.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pokemonServicePrimary")
public class PokemonService {
    private final Pokemon pokemon;

    @Autowired
    public  PokemonService(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack(){
        pokemon.attack();
    }
}
