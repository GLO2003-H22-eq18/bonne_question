package ulaval.glo2003.unit.Assembler;

import static com.google.common.truth.Truth.assertThat;
import static ulaval.glo2003.unit.Assembler.AssemblerUnitTestUtils.getSeller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.ui.assemblers.SellerAssembler;
import ulaval.glo2003.seller.ui.responses.SellerResponse;

class SellerAssemblerTest {

    private static SellerAssembler sellerAssembler;

    @BeforeAll
    public static void setUp() {
        sellerAssembler = new SellerAssembler();
    }

    @Test
    void givenSeller_whenCreateSellerResponse_thenCorrectSellerResponse() {
        Seller seller = getSeller();

        SellerResponse sellerResponse = sellerAssembler.createSellerResponse(seller);

        assertThat(sellerResponse.id).isEqualTo(seller.getId().toString());
        assertThat(sellerResponse.createdAt).isEqualTo(seller.getCreatedAt().toString());
        assertThat(sellerResponse.name).isEqualTo(seller.getName());
        assertThat(sellerResponse.bio).isEqualTo(seller.getBio());
        assertThat(sellerResponse.products).isEqualTo(seller.getProducts());
    }
}
