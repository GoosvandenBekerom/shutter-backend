package com.goosvandenbekerom.shutter.backend.controllers

import com.goosvandenbekerom.shutter.backend.domain.ImageIdResponse
import com.goosvandenbekerom.shutter.backend.services.UploadService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("upload")
class UploadController(private val service: UploadService) {
    @PostMapping
    fun requestImageServices(@RequestPart("file") file: MultipartFile) : ImageIdResponse {
        val imageId = UUID.randomUUID().toString()
        service.handleNewImage(imageId, file)
        return ImageIdResponse(imageId)
    }
}