package com.daejol.domain.usecase

import android.graphics.Bitmap
import android.os.Environment
import com.daejol.domain.AnimalType
import com.daejol.domain.entity.ImageEntity
import com.daejol.domain.repository.CatImagesRepository
import com.daejol.domain.repository.DogImagesRepository
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

/*** 이미지를 로컬 갤러리 혹은 서버 (Firebase의 DB 등)에 저장하기 위한 클래스입니다.
 *
 * [uploadImage]: 서버에 이미지를 업로드할 필요가 있을 때
 * [downloadImage]: 갤러리에 image를 다운로드할 필요가 있을 때 -> permission check와 갤러리 갱신은
 *  presentation 레이어에서 해야할 것 같다..?
 *
 ***/
class SaveImageUseCase @Inject constructor(
    catImagesRepository: CatImagesRepository,
    dogImagesRepository: DogImagesRepository,
) {

    // catdogcup 폴더를 생성하고, cat, dog 폴더를 각각 필요할 때 lazy하게 생성해서 animalType에 따라 저장한다.
    fun downloadImage(
        bitmap: Bitmap,
        imageEntity: ImageEntity
    ): File? {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state) {

            val rootPath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    .toString()
            val dirName = "/catdogcup"
            val fileName =
                imageEntity.breeds?.first()?.name + System.currentTimeMillis().toString() + ".png"
            val savePath = File(rootPath + dirName)
            savePath.mkdirs()

            val file = File(savePath, fileName)
            if (file.exists()) file.delete()

            try {
                val out = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                out.flush()
                out.close()

                //갤러리 갱신
//                context.sendBroadcast(
//                    Intent(
//                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
//                        Uri.parse("file://" + Environment.getExternalStorageDirectory())
//                    )
//                )

                return file
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }

    fun uploadImage(animalType: AnimalType) {
        // TODO: NOT IMPLEMENTATION
    }
}
