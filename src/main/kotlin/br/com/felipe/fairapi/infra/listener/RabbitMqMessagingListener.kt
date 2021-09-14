package br.com.felipe.fairapi.infra.listener

import br.com.felipe.fairapi.infra.repositories.StoreDatabaseFromCsvRepositoryImpl
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.S3ObjectInputStream
import com.rabbitmq.client.Channel
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.AmqpHeaders
import org.springframework.beans.factory.annotation.Value
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileOutputStream


@Component
class RabbitMqMessagingListener(
    private val amazonS3: AmazonS3,
    private val storeDatabaseFromCsvRepositoryImpl: StoreDatabaseFromCsvRepositoryImpl
) {

    @Value("\${aws.bucketName}")
    lateinit var bucketName: String

    @Value("\${file.path}")
    lateinit var path: String

    @RabbitListener(queues = ["queue.fairapp"])
    fun receiveMessage(message: Message, channel: Channel, @Header(AmqpHeaders.DELIVERY_TAG) tag: Long) {
        try {
            channel.basicAck(tag, false)

            val fileName = message.body.decodeToString()
            val file = File("${path}/${fileName}")
            val s3Object = amazonS3.getObject(bucketName, fileName)
            val s3Stream = s3Object.objectContent

            writeFileToDisk(file, s3Stream)

            storeDatabaseFromCsvRepositoryImpl.updateDatabase(fileName)
        } catch (ex: Exception) {
            channel.basicReject(tag, false)
            throw ex
        }
    }

    private fun writeFileToDisk(file: File, stream: S3ObjectInputStream) {
        val fos = FileOutputStream(file)
        val readBuf = ByteArray(1024)
        var readLen = 0
        while (stream.read(readBuf).also { readLen = it } > 0) {
            fos.write(readBuf, 0, readLen)
        }
        stream.close()
        fos.close()
    }


}