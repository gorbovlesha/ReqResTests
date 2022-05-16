package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceData {
    private int id;
    private String name;
    private int year;
    private String color;
    private String pantone_value;
}
