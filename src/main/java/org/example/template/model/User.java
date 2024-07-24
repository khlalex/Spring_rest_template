package org.example.template.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private Long id;
    private String name;
    private String lastName;
    private Byte age;

}
