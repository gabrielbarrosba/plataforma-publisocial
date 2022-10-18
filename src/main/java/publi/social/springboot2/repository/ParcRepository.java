package publi.social.springboot2.repository;

import publi.social.springboot2.domain.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcRepository extends JpaRepository<Parceiro, Long>{
    
}