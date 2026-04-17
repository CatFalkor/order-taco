package com.software.taco.controller;

import com.software.taco.model.Ingredient;
import com.software.taco.model.Taco;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        addIngredientsToModel(model);
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(Model model, @Valid @ModelAttribute("design") Taco design, Errors errors) {
        if(errors.hasErrors()) {
            addIngredientsToModel(model);
            return "design";
        }

        log.info("Processing taco: " + design);
        return "redirect:/orders/current";
    }

    private void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = getAllIngredients();
        for (var type : Ingredient.Type.values()) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type)
            );
        }
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

    private List<Ingredient> getAllIngredients() {
        return Arrays.asList(
                new Ingredient("FLTO", "pszenna", Ingredient.Type.WRAP),
                new Ingredient("COTO", "kukurydziana", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "fasolka czerwona", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "fasola mung", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "pomidory krojone w kostkę", Ingredient.Type.VEGGIES),
                new Ingredient("LECT", "sałata", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "cheddar", Ingredient.Type.CHEESE),
                new Ingredient("COTT", "ser gnanulowany", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "pikantny sos pomidorowy", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "śmietana", Ingredient.Type.SAUCE)

        );
    }
}