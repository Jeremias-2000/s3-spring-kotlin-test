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


    /*fun searchFile( fileName: String?): String? {
        val s3Client = s3Config.getS3Connection()

        if (fileName == null) {
            return null
        }

        try {
            // Verifica se o bucket existe
            //s3Config.headBucket { request -> request.bucket(s3Config.bucketName) }

            // Lista os objetos dentro do bucket que correspondem ao nome do arquivo
            val objects = s3Client.listObjectsV2 {
                it.bucket(s3Config.bucketName).prefix(fileName)
            }

            // Verifica se algum objeto com o nome exato ou com o mesmo nome e um formato diferente existe
            val fileNameWithoutExtension = fileName.substringBeforeLast(".")
            for (s3Object in objects.contents()) {
                val objectKey = s3Object.key()
                if (objectKey == fileName || objectKey.startsWith(fileNameWithoutExtension)) {
                    // Gera a URL do objeto encontrado
                    val url = s3Client.utilities().getUrl { it.bucket(s3Config.bucketName).key(objectKey) }
                    return url.toString()  // Retorna a URL como string
                }
            }

            return null
        } catch (exception: NoSuchBucketException) {
            return null
        } catch (exception: Exception) {
            // Captura qualquer outro erro (ex: problemas de rede, autenticação, etc)
            println("Erro ao verificar ou gerar a URL do arquivo: ${exception.message}")
            return null
        }
    }*/




    fun uploadFile(inputStream: InputStream, fileName: String) {
        val s3Client = s3Config.getS3Connection()
        println("Adicionando objeto no bucket ...")
        try {
            val metadata = ObjectMetadata()
            metadata.contentLength = inputStream.available().toLong() // Definindo o content length baseado no InputStream
            //metadata.contentType = getContentTypeFromInputStream(inputStream)
            // Criando a requisição para o upload
            val putObjectRequest = PutObjectRequest(
                s3Config.bucketName, // Nome do bucket
                fileName, // Nome do arquivo no S3
                inputStream, // InputStream contendo o arquivo
                metadata // Metadados (com Content-Length)
            )
            s3Client?.putObject(putObjectRequest)
            println("Arquivo carregado com sucesso para o bucket ${s3Config.bucketName} com a chave $fileName.")
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
        val objectList = s3Client?.listObjects(s3Config.bucketName)
        return objectList?.objectSummaries?.stream()
            ?.map { summary -> summary.key }
            ?.collect(Collectors.toList())!!
    }
    /*fun getContentTypeFromMultipartFile(file: MultipartFile): String {
        val tika = Tika()

        return tika.detect(file.inputStream()) // Detecta o Content-Type automaticamente
    }*/

    fun getContentTypeFromInputStream(inputStream: InputStream): String {
        val tika = Tika()
        return tika.detect(inputStream) // Detecta o Content-Type automaticamente
    }


}