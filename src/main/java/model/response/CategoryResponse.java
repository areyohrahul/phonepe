package model.response;

import lombok.Builder;
import lombok.Data;
import model.Product;

import java.util.List;

@Data
@Builder
public class CategoryResponse {

    private Long id;
    private List<Product> productList;
}
