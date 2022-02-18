package ulaval.glo2003.unit;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.Seller;
import ulaval.glo2003.SellerAssembler;
import ulaval.glo2003.SellerResponse;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.google.common.truth.Truth.*;

class SellerAssemblerTest {
    @Test
    void givenSeller_whenCreateSellerResponse_thenCorrectSellerResponse() {
        String name = "John Cena";
        String bio = "You can't see me, my time is now.";
        String birthDate = "1977-04-23";
        List<String> products = new ArrayList<>();
        Seller seller = new Seller(name, bio, birthDate, products);
        SellerAssembler sellerAssembler = new SellerAssembler();

        SellerResponse sellerResponse = sellerAssembler.createSellerResponse(seller);
        String responseId = sellerResponse.id;
        OffsetDateTime responseCreatedAt = sellerResponse.createdAt;
        String responseName = sellerResponse.name;
        String responseBio = sellerResponse.bio;
        List<String> responseProducts = sellerResponse.products;

//        TODO: Verify the 2 first
        assertThat(responseId).isNotNull();
        assertThat(responseCreatedAt).isNotNull();
        assertThat(responseName).isEqualTo(name);
        assertThat(responseBio).isEqualTo(bio);
        assertThat(responseProducts).isEqualTo(products);

    }
}