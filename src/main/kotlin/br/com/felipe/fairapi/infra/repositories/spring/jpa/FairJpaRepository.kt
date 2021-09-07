package br.com.felipe.fairapi.infra.repositories.spring.jpa

import br.com.felipe.fairapi.infra.models.FairEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FairJpaRepository : JpaRepository<FairEntity, Long> {
}