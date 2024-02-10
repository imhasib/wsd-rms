package com.hsb.rms.dto;

import com.hsb.rms.config.Constants;
import com.hsb.rms.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.tomcat.util.bcel.classfile.Constant;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Email
    private String email;
    @NotNull
    private String name;
    @Size(min = Constants.PASSWORD_MIN_LENGTH, max = Constants.PASSWORD_MAX_LENGTH)
    private String password;
}
