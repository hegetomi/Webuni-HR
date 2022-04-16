package hu.webuni.hr.hegetomi.repository;

import hu.webuni.hr.hegetomi.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position,Long> {
    Optional<Position> getByName(String name);
}
