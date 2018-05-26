package com.goosvandenbekerom.shutter.backend.controllers

import com.goosvandenbekerom.shutter.backend.domain.ImageIdResponse
import com.goosvandenbekerom.shutter.backend.domain.ServiceRequest
import com.goosvandenbekerom.shutter.backend.services.UploadService
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("upload")
class UploadController(private val service: UploadService) {
    @PostMapping
    fun requestImageServices(@RequestParam("file") file: MultipartFile, @RequestParam("services[]") services: Array<String>) : ImageIdResponse {
        val requestedServices = setOf("save", *services)
        val imageId = UUID.randomUUID().toString()
        val request = ServiceRequest(file, requestedServices, imageId)
        service.handleRequest(request)
        return ImageIdResponse(imageId)
    }
}