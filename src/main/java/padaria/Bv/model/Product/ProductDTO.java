package padaria.Bv.model.Product;

public record ProductDTO(Long id, String name, String description, Double price) {

    public ProductDTO(Product entity) {
        this(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice()
        );
    }
}
