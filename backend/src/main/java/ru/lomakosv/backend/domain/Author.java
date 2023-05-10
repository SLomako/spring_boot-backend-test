package ru.lomakosv.backend.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {
    public String id;
    public String firstName;
    public String lastName;
}
