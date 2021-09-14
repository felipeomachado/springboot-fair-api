package br.com.felipe.fairapi.infra.configurations

import br.com.felipe.fairapi.domain.services.*
import br.com.felipe.fairapi.infra.repositories.FairRepositoryImpl
import br.com.felipe.fairapi.infra.repositories.RabbitMqMessagingRepositoryImpl
import br.com.felipe.fairapi.infra.repositories.StoreDatabaseFromCsvRepositoryImpl
import br.com.felipe.fairapi.infra.repositories.StoreFileToAwsS3Impl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration() {

    @Bean
    fun createFairService(fairRepositoryImpl: FairRepositoryImpl): CreateFairService {
        return CreateFairService(fairRepositoryImpl)
    }

    @Bean
    fun deleteFairService(fairRepositoryImpl: FairRepositoryImpl): DeleteFairService {
        return DeleteFairService(fairRepositoryImpl)
    }

    @Bean
    fun updateFairService(fairRepositoryImpl: FairRepositoryImpl): UpdateFairService {
        return UpdateFairService(fairRepositoryImpl)
    }

    @Bean
    fun searchFairService(fairRepositoryImpl: FairRepositoryImpl): SearchFairService {
        return SearchFairService(fairRepositoryImpl)
    }

    @Bean
    fun storeDatabaseService(storeDatabaseRepositoryCSVImpl: StoreDatabaseFromCsvRepositoryImpl): StoreDatabaseService {
        return StoreDatabaseService(storeDatabaseRepositoryCSVImpl)
    }

    @Bean
    fun uploadFileService(storeFileToAwsS3Impl: StoreFileToAwsS3Impl, rabbitMqMessagingRepositoryImpl: RabbitMqMessagingRepositoryImpl): UploadFileService {
        return UploadFileService(storeFileToAwsS3Impl, rabbitMqMessagingRepositoryImpl)
    }
}