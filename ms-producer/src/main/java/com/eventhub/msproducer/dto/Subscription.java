package com.eventhub.msproducer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Subscription {

    @NotNull(message = "Email can not null")
    @NotBlank(message = "Email can not be blank")
    @Email
    private String email;

    @NotNull(message = "Birth Date can not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="GMT-3")
    private Date birthDate;

    private String firstName;

    private String gender;

}
