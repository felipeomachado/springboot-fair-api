package br.com.felipe.fairapi.domain.repositories

import br.com.felipe.fairapi.domain.models.Fair
import br.com.felipe.fairapi.domain.models.SearchFairFilter

interface FairRepository {
    fun save(fair: Fair): Fair
    fun findById(id: Long): Fair?
    fun update(id: Long, fair: Fair): Fair
    fun findAll(filter: SearchFairFilter, page: Int, size: Int): List<Fair>
}