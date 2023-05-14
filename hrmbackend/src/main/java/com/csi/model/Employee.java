package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue
    private int empId;

    @NotNull
    @Size(min = 2,message = "First name atleast have 2 characters")
    private String empFirstName;

    private String empLastName;

    @NotNull
    @Email(message = "Email id must be valid")
    private String empEmailId;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-mm-YYYY")
    private Date empDob;
}
