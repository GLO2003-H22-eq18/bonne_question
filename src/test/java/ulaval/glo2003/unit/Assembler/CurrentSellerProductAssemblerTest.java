package ulaval.glo2003.unit.Assembler;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.View;
import ulaval.glo2003.product.ui.responses.DetailedProductOffersResponse;
import ulaval.glo2003.product.ui.responses.DetailedProductViewsResponse;
import ulaval.glo2003.seller.ui.assemblers.CurrentSellerProductAssembler;
import ulaval.glo2003.seller.ui.responses.CurrentSellerProductResponse;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.product.domain.ProductCategory.toStringList;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getOffer;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getProduct;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getView;

public class CurrentSellerProductAssemblerTest {

    private static CurrentSellerProductAssembler currentSellerProductAssembler;

    @BeforeAll
    public static void setup() {
        currentSellerProductAssembler = new CurrentSellerProductAssembler();
    }

    @Test
    void givenProductWithOffer_whenCreateCurrentSellerProductResponse_thenCorrectCurrentProductSellerResponse() {
        Product product = getProduct();
        Offer offer = getOffer(25.03);
        product.addOffer(offer);

        CurrentSellerProductResponse currentSellerProductAssemblerResponse =
                currentSellerProductAssembler.createCurrentSellerProductResponse(product);

        assertThat(currentSellerProductAssemblerResponse.id).isEqualTo(product.getId().toString());
        assertThat(currentSellerProductAssemblerResponse.createdAt).isEqualTo(
                product.getCreatedAt().toString());
        assertThat(currentSellerProductAssemblerResponse.title).isEqualTo(product.getTitle());
        assertThat(currentSellerProductAssemblerResponse.suggestedPrice).isEqualTo(
                product.getSuggestedPrice());
        assertThat(currentSellerProductAssemblerResponse.description).isEqualTo(
                product.getDescription());
        assertThat(currentSellerProductAssemblerResponse.categories).isEqualTo(
                toStringList(product.getCategories()));
        assertThat(currentSellerProductAssemblerResponse.offers).isInstanceOf(
                DetailedProductOffersResponse.class);
    }

    @Test
    void givenProductWithViews_whenCreateCurrentSellerProductResponse_thenCorrectCurrentProductSellerResponse() {
        Product product = getProduct();
        View view = getView();
        product.addView(view);

        CurrentSellerProductResponse currentSellerProductAssemblerResponse =
                currentSellerProductAssembler.createCurrentSellerProductViewsResponse(product);

        assertThat(currentSellerProductAssemblerResponse.id).isEqualTo(product.getId().toString());
        assertThat(currentSellerProductAssemblerResponse.createdAt).isEqualTo(
                product.getCreatedAt().toString());
        assertThat(currentSellerProductAssemblerResponse.title).isEqualTo(product.getTitle());
        assertThat(currentSellerProductAssemblerResponse.suggestedPrice).isEqualTo(
                product.getSuggestedPrice());
        assertThat(currentSellerProductAssemblerResponse.description).isEqualTo(
                product.getDescription());
        assertThat(currentSellerProductAssemblerResponse.categories).isEqualTo(
                toStringList(product.getCategories()));
        assertThat(currentSellerProductAssemblerResponse.views).isInstanceOf(
                DetailedProductViewsResponse.class);
    }

    @Test
    void givenProductWithNoViews_whenCreateCurrentSellerProductResponse_thenCorrectCurrentProductSellerResponse() {
        Product product = getProduct();

        CurrentSellerProductResponse currentSellerProductAssemblerResponse =
                currentSellerProductAssembler.createCurrentSellerProductViewsResponse(product);

        assertThat(currentSellerProductAssemblerResponse.id).isEqualTo(product.getId().toString());
        assertThat(currentSellerProductAssemblerResponse.createdAt).isEqualTo(
                product.getCreatedAt().toString());
        assertThat(currentSellerProductAssemblerResponse.title).isEqualTo(product.getTitle());
        assertThat(currentSellerProductAssemblerResponse.suggestedPrice).isEqualTo(
                product.getSuggestedPrice());
        assertThat(currentSellerProductAssemblerResponse.description).isEqualTo(
                product.getDescription());
        assertThat(currentSellerProductAssemblerResponse.categories).isEqualTo(
                toStringList(product.getCategories()));
        assertThat(currentSellerProductAssemblerResponse.views).isInstanceOf(
                DetailedProductViewsResponse.class);
    }

    @Test
    void givenProductNoOffer_whenCreateCurrentSellerProductResponse_thenCorrectCurrentProductSellerResponse() {
        Product product = getProduct();

        CurrentSellerProductResponse currentSellerProductAssemblerResponse =
                currentSellerProductAssembler.createCurrentSellerProductResponse(product);

        assertThat(currentSellerProductAssemblerResponse.id).isEqualTo(product.getId().toString());
        assertThat(currentSellerProductAssemblerResponse.createdAt).isEqualTo(
                product.getCreatedAt().toString());
        assertThat(currentSellerProductAssemblerResponse.title).isEqualTo(product.getTitle());
        assertThat(currentSellerProductAssemblerResponse.suggestedPrice).isEqualTo(
                product.getSuggestedPrice());
        assertThat(currentSellerProductAssemblerResponse.description).isEqualTo(
                product.getDescription());
        assertThat(currentSellerProductAssemblerResponse.categories).isEqualTo(
                toStringList(product.getCategories()));
        assertThat(currentSellerProductAssemblerResponse.offers).isInstanceOf(
                DetailedProductOffersResponse.class);
    }
}
