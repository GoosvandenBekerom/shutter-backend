package com.goosvandenbekerom.shutter.backend.services

import com.goosvandenbekerom.shutter.backend.domain.ServiceRequest
import org.springframework.stereotype.Service

@Service
class UploadService {
    fun handleRequest(request: ServiceRequest) = println(request) // TODO: create kafka event bus for requested services
}