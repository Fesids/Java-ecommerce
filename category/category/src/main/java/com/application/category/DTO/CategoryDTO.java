package com.application.category.DTO;

public record CategoryDTO(

        /*@NotEmpty @NotNull*/ String name,

        /*@NotEmpty @NotNull*/ Boolean available

) {
}
