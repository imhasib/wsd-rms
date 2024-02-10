package com.hsb.rms.dto;

import com.hsb.rms.domain.User;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String name;
    private boolean activated;

    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.activated = user.isActivated();
    }
}
