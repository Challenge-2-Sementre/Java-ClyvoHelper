package fiap._tdspo.clyvoHelper.clyvoHelper.repository;

import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Page<Animal> findByEspecie(String especie, Pageable pageable);

}