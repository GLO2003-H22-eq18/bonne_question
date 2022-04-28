package ulaval.glo2003.product.ui.responses;

public class ViewResponse {
    public String id;
    public String createdAt;

    public ViewResponse() {
        super();
    }

    public ViewResponse(String id,
                        String createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }
}
