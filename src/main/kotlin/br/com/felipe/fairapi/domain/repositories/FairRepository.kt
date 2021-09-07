package br.com.felipe.fairapi.domain.repositories

import br.com.felipe.fairapi.domain.models.Fair

interface FairRepository {
    fun save(fair: Fair): Fair
    fun findById(id: Long): Fair?
}