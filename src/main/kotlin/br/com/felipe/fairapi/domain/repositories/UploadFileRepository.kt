package br.com.felipe.fairapi.domain.repositories

import java.io.File

interface UploadFileRepository {
    fun uploadFile(file: File)
}