package br.com.felipe.fairapi.infra.configurations

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig {

    @Value("\${aws.region}")
    lateinit var region: String;

    @Value("\${aws.key}")
    lateinit var key: String;

    @Value("\${aws.secret}")
    lateinit var secret: String;

    @Bean
    fun authenticationS3() : AmazonS3 {
        return AmazonS3ClientBuilder
            .standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(key, secret)))
            .build()
    }

}