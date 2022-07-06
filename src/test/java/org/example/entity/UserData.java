package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Here we can also use annotation @JsonIgnoreProperties(ignoreUnknown = true)
 * to ignore some unknown props (or props that we dont need)
 */
@Data
@NoArgsConstructor
public class UserData {
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

}
