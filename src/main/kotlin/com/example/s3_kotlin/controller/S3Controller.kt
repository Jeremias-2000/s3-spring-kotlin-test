package com.example.s3_kotlin.controller

import com.amazonaws.services.s3.model.AmazonS3Exception
import com.example.s3_kotlin.dto.S3FIleDto
import com.example.s3_kotlin.service.S3FileService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import java.util.stream.Collectors

@RestController
@RequestMapping("/")
class S3Controller(
    val s3FileService: S3FileService
) {

    companion object{
        private const val FILE_NOT_EXISTS= "Arquivo nao encontrado"
    }


    @PostMapping("/s3/files/upload")
    fun createFile(@RequestParam file:MultipartFile) :ResponseEntity<S3FIleDto>{
        try {
            val inputStream: InputStream = file.inputStream
            s3FileService.uploadFile(inputStream, file.originalFilename ?: "unknown-object")

        } catch (e: AmazonS3Exception){
            return ResponseEntity(S3FIleDto(e.message),HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e:Exception){
            return ResponseEntity(S3FIleDto(e.message),HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity.ok(S3FIleDto(file.originalFilename))

    }

    @GetMapping("/s3/files")
    fun contentFile(): ResponseEntity<List<S3FIleDto>> {

            val found = s3FileService.fetchFiles()
            val files:MutableList<S3FIleDto> = ArrayList()
           found.stream().map { f -> files.add(S3FIleDto(f)) }.collect(Collectors.toList())


        return ResponseEntity(files,HttpStatus.OK)

    }
}