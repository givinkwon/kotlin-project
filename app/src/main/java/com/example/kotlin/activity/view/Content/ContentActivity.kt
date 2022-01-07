package com.example.myapplication.activity

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.content.*

class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content)


        val VIDEO_PATH = "android.resource://" + packageName + "/" + R.raw.video

        var video = Uri.parse(VIDEO_PATH)
        content_video.setVideoURI(video)
        content_video.setMediaController(MediaController(this))     // 없으면 에러
        content_video.requestFocus()    // 준비하는 과정을 미리함

        content_video.setOnPreparedListener {
            Toast.makeText(applicationContext, "동영상 재생 준비 완료", Toast.LENGTH_SHORT).show()
            content_video.start()       // 동영상 재개
        }

        content_video.setOnCompletionListener {
            Toast.makeText(applicationContext, "동영상 시청 완료", Toast.LENGTH_SHORT).show()
        }
    }
}