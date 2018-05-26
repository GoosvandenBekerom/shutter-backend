package com.goosvandenbekerom.shutter.backend.domain

import org.springframework.web.multipart.MultipartFile

data class ServiceRequest(val multipartFile: MultipartFile, val requestedServices: Set<String>, val imageId: String)