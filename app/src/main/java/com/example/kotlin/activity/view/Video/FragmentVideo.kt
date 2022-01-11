package com.example.kotlin.activity.view.Video

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.browser.customtabs.CustomTabsClient.getPackageName
import com.example.myapplication.R
import com.google.common.reflect.Reflection.getPackageName
import kotlinx.android.synthetic.main.fragment_video.*

class FragmentVideo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layouts for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val VIDEO_PATH = "android.resource://" + requireActivity().packageName + "/" + R.raw.video

        var video = Uri.parse(VIDEO_PATH)
        content_video.setVideoURI(video)
        content_video.setMediaController(MediaController(requireContext()))     // 없으면 에러
        content_video.requestFocus()    // 준비하는 과정을 미리함

        content_video.setOnPreparedListener {
            content_video.start()       // 동영상 재개
        }

        content_video.setOnCompletionListener {
        }
    }

}