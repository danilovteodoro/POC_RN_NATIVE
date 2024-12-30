package io.ckl.rnpoc.util

import android.content.Context
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.BufferedReader
import java.io.File
import java.io.InputStream

class FileUtils {

    companion object {
        private const val TAG = "FileUtils"
        private const val FILE_NAME = "index.android.bundle"
        private val storage = Firebase.storage

        fun saveFile(context: Context, value: String){
//        context.openFileOutput(FILE_NAME,Context.MODE_PRIVATE).use {
//            it.write(value.toByteArray())
//        }

            with(context.getFileStreamPath(FILE_NAME)){
                Log.d(TAG,"Absolute File Path: $absolutePath")
            }
        }

        fun getJSBundleLocation(context: Context): String{
            return context.getFileStreamPath(FILE_NAME).absolutePath
        }

        fun copyBundleFile(context: Context){
            context.assets.open(FILE_NAME).use {
                val content = it.bufferedReader().use(BufferedReader::readText)
                context.openFileOutput(FILE_NAME,Context.MODE_PRIVATE).use {
                    it.write(content.toByteArray())
                }
            }
        }

        fun downloadFromStorage(context: Context) {
            val ref = storage.getReferenceFromUrl("gs://rnpoc-44df0.appspot.com/")
                .child(FILE_NAME)

            ref.getBytes(1024*1024).addOnSuccessListener {
                saveBundleFile(context,it)
            }.addOnFailureListener {err: Exception->
                Log.e(TAG,"Download error",err)
            }.addOnCanceledListener {
                Log.d(TAG,"Canceled")
            }
        }

        private fun saveBundleFile(context: Context, data: ByteArray){
            context.openFileOutput(FILE_NAME,Context.MODE_PRIVATE).use {
                it.write(data)
            }
        }



    }

}
