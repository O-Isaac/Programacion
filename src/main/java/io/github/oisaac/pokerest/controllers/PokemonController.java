package io.github.oisaac.pokerest.controllers;

import io.github.oisaac.pokerest.models.PokemonModel;
import io.github.oisaac.pokerest.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping
    public ArrayList<PokemonModel> getPokemons () {
        return this.pokemonService.getPokemons();
    }

    @PostMapping("/save")
    public PokemonModel savePokemon(@RequestBody PokemonModel pokemonModel) {
        return pokemonService.savePokemon(pokemonModel);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PokemonModel> getPokemonById (@PathVariable("id") Long id) {
        return pokemonService.getPokemonById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PokemonModel> updatePokemonById(@PathVariable("id") Long id, @RequestBody PokemonModel request) {
        return pokemonService.updatePokemonById(id, request)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PokemonModel> deletePokemonById(@PathVariable("id") Long id) {
        return pokemonService.deletePokemonById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
