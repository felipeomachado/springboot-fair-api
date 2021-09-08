package br.com.felipe.fairapi.infra.repositories

import br.com.felipe.fairapi.domain.exceptions.EntityNotFoundException
import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.models.SearchFairFilter
import br.com.felipe.fairapi.domain.repositories.FairRepository
import br.com.felipe.fairapi.infra.models.FairEntity
import br.com.felipe.fairapi.infra.repositories.spring.jpa.FairJpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Repository
class FairRepositoryImpl(
    private val jpaRepository: FairJpaRepository,
    private val entityManager: EntityManager
) : FairRepository {

    override fun save(fair: Fair): Fair {
        val fairEntity = FairEntity(fair)
        return jpaRepository.save(fairEntity).toModel()
    }

    override fun findById(id: Long): Fair {
        val fairEntity =
            jpaRepository.findById(id).orElseThrow { EntityNotFoundException("Fair with id: $id not found!") }
        return fairEntity.toModel()
    }

    override fun update(id: Long, fair: Fair): Fair {
        val fairEntity =
            jpaRepository.findById(id).orElseThrow { EntityNotFoundException("Fair with id: $id not found!") }
        return jpaRepository.save(
            fairEntity.copy(
                longitude = fair.longitude,
                latitude = fair.latitude,
                sector = fair.sector,
                area = fair.area,
                districtCode = fair.districtCode,
                districtName = fair.districtName,
                subCityHallCode = fair.subCityHallCode,
                subCityHallName = fair.subCityHallName,
                region5 = fair.region5,
                region8 = fair.region8,
                name = fair.name,
                register = fair.register,
                street = fair.street,
                number = fair.number,
                neighborhood = fair.neighborhood,
                reference = fair.reference,
                createdAt = fair.createdAt,
                updatedAt = fair.updatedAt,
            )
        ).toModel()
    }

    override fun findAll(filter: SearchFairFilter, page: Int, size: Int): List<Fair> {

        val criteriaBuilder: CriteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery: CriteriaQuery<FairEntity> = criteriaBuilder.createQuery(
            FairEntity::class.java
        )

        val fairRoot: Root<FairEntity> = criteriaQuery.from(FairEntity::class.java)
        val predicates = mutableListOf<Predicate>()

        filter.district?.let {
            predicates.add(criteriaBuilder.equal(fairRoot.get<String>("districtName"), it))
        }

        filter.region5?.let {
            predicates.add(criteriaBuilder.equal(fairRoot.get<String>("region05"), it))
        }

        filter.fairName?.let {
            predicates.add(criteriaBuilder.equal(fairRoot.get<String>("name"), it))
        }

        filter.neighborhood?.let {
            predicates.add(criteriaBuilder.equal(fairRoot.get<String>("neighborhood"), it))
        }

        predicates.add(criteriaBuilder.equal(fairRoot.get<String>("active"), true))

        criteriaQuery.where(*predicates.toTypedArray())

        val resultList = entityManager.createQuery(criteriaQuery)
            .setFirstResult((page-1)*size)
            .setMaxResults(size)
            .resultList

        return resultList.map {
            it.toModel()
        }.toList()
    }
}