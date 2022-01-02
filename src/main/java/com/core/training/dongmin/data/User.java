package com.core.training.dongmin.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@ToString
public class User {

    @NotEmpty(message = "{validator.notEmpty}")
    private String id;

    @NotEmpty(message = "{validator.notEmpty}")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^[0-9]+$", message = "{validator.numberCheck}")
    @DecimalMin(value = "14", message = "{validator.DecimalMin}")
    private String age;

    public User(){

    }

    public User(String id, String name, String age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
