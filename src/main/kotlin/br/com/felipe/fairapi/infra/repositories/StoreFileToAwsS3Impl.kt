package br.com.felipe.fairapi.infra.repositories

import br.com.felipe.fairapi.domain.repositories.UploadFileRepository
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File

@Component
class StoreFileToAwsS3Impl(
    private val amazonS3: AmazonS3
): UploadFileRepository {
    @Value("\${file.path}")
    lateinit var path: String

    @Value("\${aws.bucketName}")
    lateinit var bucketName: String


    override fun uploadFile(file: File) {
        amazonS3.putObject(PutObjectRequest(bucketName, file.name, file))
    }
}