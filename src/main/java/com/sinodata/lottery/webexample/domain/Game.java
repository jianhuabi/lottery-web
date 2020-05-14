package com.sinodata.lottery.webexample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Game {
    @Id
    Long id;
    String name;

    public Game() {

    }
}
