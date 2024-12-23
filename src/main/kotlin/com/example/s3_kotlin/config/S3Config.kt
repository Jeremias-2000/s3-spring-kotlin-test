package com.example.s3_kotlin.config

import com.amazonaws.ApacheHttpClientConfig
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import java.net.URI


@Configuration
class S3Config(
    @Value("\${cloud.aws.credentials.access-key}")
    var accessKey:String = "AKIAIOSFODNN7EXAMPLE",
    @Value("\${cloud.aws.credentials.secret-key}")
    var secretKey:String = "test",
    @Value("\${cloud.aws.credentials.bucket-name}")
    var bucketName:String = "s3-test"
) {
    @PostConstruct
    fun init() {
        println("Access Key: $accessKey")
        println("Secret Key: $secretKey")
        println("Bucket Name: $bucketName")

    }

    @Bean
    fun getS3Connection(): AmazonS3? {
        println("Autenticando S3...")

        val s3Client= AmazonS3ClientBuilder
            .standard()
            .withPathStyleAccessEnabled(true)
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:4566",Regions.US_EAST_1.toString()))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()


        val listBucketsResponse = s3Client.listBuckets()
        println("Buckets: ${listBucketsResponse}")
        return s3Client
    }

}