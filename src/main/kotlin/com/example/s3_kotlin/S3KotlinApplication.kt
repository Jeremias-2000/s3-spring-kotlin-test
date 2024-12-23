package com.example.s3_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class S3KotlinApplication

fun main(args: Array<String>) {
	runApplication<S3KotlinApplication>(*args)
}
