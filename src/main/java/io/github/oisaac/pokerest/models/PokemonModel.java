package io.github.oisaac.pokerest.models;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemons")
public class PokemonModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "collectionNo", nullable = false)
    private int collectionNo;

    @Column(name = "url", nullable = false)
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCollectionNo() {
        return collectionNo;
    }

    public void setCollectionNo(Integer collectionNo) {
        this.collectionNo = collectionNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
