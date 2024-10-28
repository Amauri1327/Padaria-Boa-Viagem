package padaria.Bv.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public ProductDTO insert(ProductDTO dto) {
        Product obj = new Product();
        obj.setName(dto.name());
        obj.setDescription(dto.description());
        obj.setPrice(dto.price());
        repository.save(obj);
        return new ProductDTO(obj);
    }

    public ProductDTO update(Long id, ProductDTO dto) {

        Product prod = repository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        prod.setName(dto.name());
        prod.setDescription(dto.description());
        prod.setPrice(dto.price());
        repository.save(prod);
        return new ProductDTO(prod);
    }

    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Id not found: " + id);
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new RuntimeException("Integrity violation");
        }
    }

}
