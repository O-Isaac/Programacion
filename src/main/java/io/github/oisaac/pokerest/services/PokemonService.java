package io.github.oisaac.pokerest.services;

import io.github.oisaac.pokerest.models.PokemonModel;
import io.github.oisaac.pokerest.repositories.IPokemonRepository;
import io.github.oisaac.pokerest.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    IPokemonRepository pokemonRepository;



    public ArrayList<PokemonModel> getPokemons() {
        return (ArrayList<PokemonModel>) pokemonRepository.findAll();
    }

    public PokemonModel savePokemon(PokemonModel pokemonModel) {
        return pokemonRepository.save(pokemonModel);
    }

    public Optional<PokemonModel> getPokemonById (Long id) {
        return pokemonRepository.findById(id);
    }

    public Optional<PokemonModel> updatePokemonById(Long id, PokemonModel request) {
        Optional<PokemonModel> optionalPokemonModel = pokemonRepository.findById(id);

        if (optionalPokemonModel.isPresent()) {
            PokemonModel pokemon = optionalPokemonModel.get();

            Utils.replaceIfPresent(request::getName, pokemon::setName);
            Utils.replaceIfPresent(request::getUrl, pokemon::setUrl);
            Utils.replaceIfPresent(request::getCollectionNo, pokemon::setCollectionNo);

            return Optional.of(pokemonRepository.save(pokemon));
        }

        return Optional.empty();
    }

    public Optional<PokemonModel> deletePokemonById(Long id) {
        Optional<PokemonModel> pokemon = pokemonRepository.findById(id);

        if (pokemon.isPresent()) {
            pokemonRepository.deleteById(id);
        }

        return pokemon;
    }

}
