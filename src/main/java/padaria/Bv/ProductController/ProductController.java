package padaria.Bv.ProductController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import padaria.Bv.model.Product.ProductDTO;
import padaria.Bv.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> dto = service.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
