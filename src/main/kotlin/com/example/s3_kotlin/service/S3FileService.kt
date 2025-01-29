package com.example.s3_kotlin.service

import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.s3_kotlin.config.S3Config
import org.apache.tika.Tika
import org.springframework.stereotype.Service
import java.io.InputStream
import java.util.stream.Collectors

@Service
class S3FileService(
    val s3Config: S3Config,
) {


    fun uploadFile(inputStream: InputStream, fileName: String) {
        val s3Client = s3Config.getS3Connection()
        println("Adicionando objeto no bucket ...")
        try {
            val metadata = ObjectMetadata()
            metadata.contentLength = inputStream.available().toLong()
            //metadata.contentType = getContentTypeFromInputStream(inputStream)

            val putObjectRequest = PutObjectRequest(
                s3Config.s3Bucket,
                fileName,
                inputStream,
                metadata
            )
            s3Client?.putObject(putObjectRequest)
            println("Arquivo carregado com sucesso para o bucket ${s3Config.s3Bucket} com a chave $fileName.")
        }catch (e: AmazonS3Exception){
            println("Erro ao carregar arquivo: ${e}")
            e.printStackTrace()
        }
        catch (e: Exception) {
            println("Erro ao carregar arquivo: ${e}")
            e.printStackTrace()
        }
    }

    fun fetchFiles():List<String> {

        println("Verificando arquivos salvos")
        val s3Client = s3Config.getS3Connection()
        val objectList = s3Client?.listObjects(s3Config.s3Bucket)
        return objectList?.objectSummaries?.stream()
            ?.map { summary -> summary.key }
            ?.collect(Collectors.toList())!!
    }

}