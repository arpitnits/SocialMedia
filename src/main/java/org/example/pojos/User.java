package org.example.pojos;

import lombok.Builder;
import lombok.Data;
import org.example.enums.UserType;

@Data
@Builder
public class User {

    private String uuid;
    private String name;
    private String email;
    private boolean isActive;
    private UserType userType;
}
