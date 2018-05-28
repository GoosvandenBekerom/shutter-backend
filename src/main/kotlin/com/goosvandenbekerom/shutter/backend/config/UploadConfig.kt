package com.goosvandenbekerom.shutter.backend.config

import org.springframework.http.MediaType

object UploadConfig {
    val allowedFileTypes = setOf(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE)
}