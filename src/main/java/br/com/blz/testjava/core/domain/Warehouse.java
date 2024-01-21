package br.com.blz.testjava.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class Warehouse {


    private String locality;

    private Long quantity;

    private String type;


}
