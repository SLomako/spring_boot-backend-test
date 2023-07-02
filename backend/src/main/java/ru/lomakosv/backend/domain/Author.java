package ru.lomakosv.backend.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {

    private String id;
    private String firstName;
    private String lastName;
}
