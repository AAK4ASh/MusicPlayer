package com.main.musicplayer

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.main.musicplayer.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var musicAdapter: MusicAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        getAllAudios()
    }
    @SuppressLint("Range")
    private fun getAllAudios():MutableList<Music>{
        val tempList= mutableListOf<Music>()
        val selection =  MediaStore.Audio.Media.IS_MUSIC+ "!=0"
        val projection = arrayOf(MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media.DATA)
        val cursor= this.contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,projection,selection,null,null)
if (cursor!=null){
    if (cursor.moveToFirst())
        do {
val  title= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
            val idC =cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
            val music= Music(id = idC, musicName = title, path = String())
            val file= File(music.path)
            if (file.exists())
                tempList.add(music)
        }while (cursor.moveToNext())
        cursor.close()
}
        return tempList
    }

    private fun requestPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
    }


}



