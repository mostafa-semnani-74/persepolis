package ir.mosi.persepolis.security.model.repository;

import ir.mosi.persepolis.security.model.domain.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByName(String roleName);
}
