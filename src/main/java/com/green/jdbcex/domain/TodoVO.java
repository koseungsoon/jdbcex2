package com.green.jdbcex.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {

    private long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;

}
