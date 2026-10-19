package toko;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  record Product(int id, String name) {}
  Map<Integer, Product> data =
      Map.of(42, new Product(42, "Keyboard"));

  @GetMapping("/{id}")
  public ResponseEntity<Product> get(
      @PathVariable int id) {
    Product p = data.get(id);
    return p == null
        ? ResponseEntity.notFound().build()
        : ResponseEntity.ok(p);
  }
}