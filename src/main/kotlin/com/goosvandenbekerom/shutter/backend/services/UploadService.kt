package com.goosvandenbekerom.shutter.backend.services

import com.goosvandenbekerom.shutter.backend.config.KafkaConfig
import com.goosvandenbekerom.shutter.backend.config.UploadConfig
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFutureCallback
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.UnsupportedMediaTypeStatusException

@Service
class UploadService(private val kafka: KafkaTemplate<String, ByteArray>) {
    fun handleNewImage(imageId: String, file: MultipartFile) {
        if (!UploadConfig.allowedFileTypes.contains(file.contentType)) {
            throw UnsupportedMediaTypeStatusException("File type '${file.contentType}' is not supported")
        }

        val future = kafka.send(KafkaConfig.TOPIC_NEW_IMAGE, imageId, file.bytes)
        future.addCallback(object : ListenableFutureCallback<SendResult<String, ByteArray>> {
            override fun onSuccess(result: SendResult<String, ByteArray>?) = println("Image with id $imageId successfully sent to topic")
            override fun onFailure(ex: Throwable) = println("Sending image to kafka threw an exception: ${ex.message}")
        })
    }
}