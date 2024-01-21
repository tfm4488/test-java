package br.com.blz.testjava.adapters.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateProductRequest {

    @NotNull
    private Long sku;
    @NotNull
    private String name;
    @NotNull
    private List<Warehouse> warehouses;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Warehouse{

        @NotNull
        private String locality;

        @NotNull
        private Long quantity;

        @NotNull
        private String type;

    }
}
