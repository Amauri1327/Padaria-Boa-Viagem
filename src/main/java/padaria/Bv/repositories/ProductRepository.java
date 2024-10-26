package padaria.Bv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import padaria.Bv.model.Product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
