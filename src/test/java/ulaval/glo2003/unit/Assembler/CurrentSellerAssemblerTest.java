package ulaval.glo2003.unit.Assembler;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.ui.assemblers.CurrentSellerAssembler;
import ulaval.glo2003.seller.ui.responses.CurrentSellerResponse;

import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getOffer;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getProduct;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getSeller;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getView;

public class CurrentSellerAssemblerTest {

    private static CurrentSellerAssembler currentSellerAssembler;

    @BeforeAll
    public static void setup() {
        currentSellerAssembler = new CurrentSellerAssembler();
    }

    @Test
    void givenSellerWithProduct_whenCreateCurrentSellerResponse_thenCorrectSellerResponse() {
        Seller seller = getSeller();
        Product product = getProduct();
        Offer offer = getOffer(25.0);
        seller.addProduct(product);
        product.addOffer(offer);

        CurrentSellerResponse currentSellerResponse =
                currentSellerAssembler.createCurrentSellerResponse(seller);

        assertThat(currentSellerResponse.name).isEqualTo(seller.getName());
        assertThat(currentSellerResponse.id).isEqualTo(seller.getId().toString());
        assertThat(currentSellerResponse.bio).isEqualTo(seller.getBio());
        assertThat(currentSellerResponse.birthDate).isEqualTo(seller.getBirthDate().toString());
        assertThat(currentSellerResponse.createdAt).isEqualTo(seller.getCreatedAt().toString());
        assertThat(currentSellerResponse.products.size()).isEqualTo(seller.getProducts().size());
    }

    @Test
    void givenSellerWithProductAndView_whenCreateCurrentSellerResponse_thenCorrectSellerResponse() {
        Seller seller = getSeller();
        Product product = getProduct();
        Offer offer = getOffer(25.0);
        View view = getView();
        seller.addProduct(product);
        product.addOffer(offer);
        product.addView(view);

        CurrentSellerResponse currentSellerResponse =
                currentSellerAssembler.createCurrentSellerViewsResponse(seller);

        assertThat(currentSellerResponse.name).isEqualTo(seller.getName());
        assertThat(currentSellerResponse.id).isEqualTo(seller.getId().toString());
        assertThat(currentSellerResponse.bio).isEqualTo(seller.getBio());
        assertThat(currentSellerResponse.birthDate).isEqualTo(seller.getBirthDate().toString());
        assertThat(currentSellerResponse.createdAt).isEqualTo(seller.getCreatedAt().toString());
        assertThat(currentSellerResponse.products.size()).isEqualTo(seller.getProducts().size());
    }

    @Test
    void givenSellerNoProduct_whenCreateCurrentSellerResponse_thenCorrectSellerResponse() {
        Seller seller = getSeller();

        CurrentSellerResponse currentSellerResponse =
                currentSellerAssembler.createCurrentSellerResponse(seller);

        assertThat(currentSellerResponse.name).isEqualTo(seller.getName());
        assertThat(currentSellerResponse.id).isEqualTo(seller.getId().toString());
        assertThat(currentSellerResponse.bio).isEqualTo(seller.getBio());
        assertThat(currentSellerResponse.birthDate).isEqualTo(seller.getBirthDate().toString());
        assertThat(currentSellerResponse.createdAt).isEqualTo(seller.getCreatedAt().toString());
        assertThat(currentSellerResponse.products.size()).isEqualTo(0);
    }
}
