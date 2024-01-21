package br.com.blz.testjava.adapters.controller.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class FindProductResponse {

    private Long sku;
    private String name;
    private Inventory inventory;
    private Boolean isMarketable;

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Inventory {
        private List<Warehouse> warehouses;

        private Long quantity;

        @Builder
        @Getter
        @Setter
        @AllArgsConstructor
        public static class Warehouse {

            private String locality;

            private Long quantity;

            private String type;

        }


    }
}
