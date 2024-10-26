package padaria.Bv.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import padaria.Bv.model.Product.Product;
import padaria.Bv.model.Product.ProductDTO;
import padaria.Bv.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> findAll(){
        List<Product> prod = repository.findAll();
        return prod.stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .collect(Collectors.toList());
    }

    public ProductDTO findById(Long id) {
        Optional<Product> prod = repository.findById(id);
        Product entity = prod.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado" + id));
        return new ProductDTO(entity);
    }



}
