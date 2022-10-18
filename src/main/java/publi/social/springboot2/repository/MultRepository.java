package publi.social.springboot2.repository;

import publi.social.springboot2.domain.Multirao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultRepository extends JpaRepository<Multirao, Long>{
    
}
