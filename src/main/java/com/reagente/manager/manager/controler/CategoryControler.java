package com.reagente.manager.manager.controler;


import com.reagente.manager.manager.controler.dto.ReagenteDTO;
import com.reagente.manager.manager.entity.Category;
import com.reagente.manager.manager.entity.Reagente;
import com.reagente.manager.manager.repository.CategoryRepository;
import com.reagente.manager.manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
public class CategoryControler {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Category salvar(@RequestBody @Valid Category category){

        category.setNome(category.getNome());

        return categoryRepository.save(category);
    }

    @GetMapping("{id}")
    public Category getCategoryId(@PathVariable Integer id){
        Category category = categoryService.getCategoryDetalhes(id);
        Link link = linkTo(CategoryControler.class).withRel("Lista de categorias");
        category.add(link);
        return (category);
    }

}
