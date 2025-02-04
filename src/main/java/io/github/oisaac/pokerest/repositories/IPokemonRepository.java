package io.github.oisaac.pokerest.repositories;

import io.github.oisaac.pokerest.models.PokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPokemonRepository extends JpaRepository<PokemonModel, Long> {

}
