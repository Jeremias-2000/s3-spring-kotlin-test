package com.example.s3_kotlin.config

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


@Configuration
class S3Config(
    @Value("\${cloud.aws.credentials.access-key}")
    val accessKey:String ,
    @Value("\${cloud.aws.credentials.secret-key}")
    val secretKey:String,
    @Value("\${cloud.aws.services.s3.url}")
    val s3Url:String,
    @Value("\${cloud.aws.services.s3.bucket}")
    val s3Bucket:String
) {
    @PostConstruct
    fun init() {
        println("Access Key: $accessKey")
        println("Secret Key: $secretKey")
        println("Bucket Name: $s3Bucket")

    }

    @Bean
    fun getS3Connection(): AmazonS3? {
        println("Authenticating into S3...")

        val s3Client= AmazonS3ClientBuilder
            .standard()
            .withPathStyleAccessEnabled(true)
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(s3Url,Regions.US_EAST_1.toString()))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()


        val listBucketsResponse = s3Client.listBuckets()
        println("Buckets: ${listBucketsResponse}")
        return s3Client
    }

}