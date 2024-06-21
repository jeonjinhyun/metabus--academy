package com.ohgiraffers.section02.annotaion.subsection02.qualifier;

import com.ohgiraffers.section02.annotaion.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pokemonServiceQualifier")
public class PokemonService {

//    @Autowired
//    @Qualifier("pikachu")
    private Pokemon pokemon;

    @Autowired
    public PokemonService( @Qualifier("squirtle") Pokemon pokemon) {
        this.pokemon =pokemon;
    }

    public void pokemonAttack(){
        pokemon.attack();
    }
}
