package pl.lukpra.kalkulators.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukpra.kalkulators.models.entity.CountryEntity;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    Optional<CountryEntity> findByCountryCode(String countryCode);
    Boolean existsByCountryCode(String countryCode);

    Long deleteByCountryCode(String countryCode);
}
