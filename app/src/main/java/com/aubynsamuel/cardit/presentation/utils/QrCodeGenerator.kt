package com.aubynsamuel.cardit.presentation.utils

import android.graphics.Bitmap
import android.graphics.Color
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import java.util.EnumMap

object QrCodeGenerator {
    fun generate(contactData: String): Bitmap? {
        return try {
            val hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
            hints[EncodeHintType.MARGIN] = 1

            val qrCodeWriter = QRCodeWriter()
            val bitMatrix = qrCodeWriter.encode(
                contactData,
                BarcodeFormat.QR_CODE,
                512, // Width
                512, // Height
                hints
            )

            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = createBitmap(width, height)

            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap[x, y] = if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                }
            }
            bitmap
        } catch (e: Exception) {
            println("QR Code generation failed: ${e.message}")
            null
        }
    }
}
