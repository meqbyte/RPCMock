package com.maeq.entity;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userName;
    private Boolean sex;
}
