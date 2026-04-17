package com.software.taco.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min=5, message="Nazwa musi się składać z przynajmniej 5 znaków")
    private String name;
    @Size(min=1, message="Musisz wybrać przynajmniej 1 składnik")
    private List<String> ingredients;
}