package ulaval.glo2003.unit.Assembler;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.ui.assemblers.ProductSellerAssembler;
import ulaval.glo2003.product.ui.responses.ProductSellerResponse;

import static com.google.common.truth.Truth.assertThat;

public class ProductSellerAssemblerTest {

    private static final String NAME = "John";
    private static final ObjectId ID = new ObjectId();
    private static ProductSellerAssembler productSellerAssembler;

    @BeforeAll
    public static void setup() {
        productSellerAssembler = new ProductSellerAssembler();
    }

    @Test
    void givenSellerNameAndId_whenCreateProductSellerResponse_thenCorrectProductSellerResponse() {
        ProductSellerResponse productSellerResponse =
                productSellerAssembler.createProductSellerResponse(ID, NAME);

        assertThat(productSellerResponse.name).isEqualTo(NAME);
        assertThat(productSellerResponse.id).isEqualTo(ID.toString());
    }
}
