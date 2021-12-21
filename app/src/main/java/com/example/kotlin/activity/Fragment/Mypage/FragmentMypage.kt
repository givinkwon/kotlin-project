package com.example.kotlin.activity.Fragment.Mypage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.kotlin.activity.Fragment.Mypage.Fragment.Fan.FragmentMypageFan
import com.example.kotlin.activity.Fragment.Mypage.Fragment.Feed.FragmentMypageFeed
import com.example.kotlin.activity.Fragment.Mypage.Fragment.My.FragmentMypageMy
import com.example.kotlin.activity.data.form.User
import com.example.kotlin.activity.data.viewmodel.CurrentUserViewModel
import com.example.kotlin.activity.data.viewmodel.firestore
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_mypage.*
import java.util.*


class FragmentMypage : Fragment() {

    private val fragmentMypageFan by lazy { FragmentMypageFan() }
    private val fragmentMypageFeed by lazy { FragmentMypageFeed() }
    private val fragmentMypageMy by lazy { FragmentMypageMy() }
    private lateinit var auth: FirebaseAuth
    private val viewmodel by lazy { ViewModelProvider(this).get(CurrentUserViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private var currentUserData : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layouts for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val childFragment: FragmentManager = childFragmentManager
        viewmodel.getData().observeOn(Schedulers.newThread()).subscribeOn(Schedulers.io()).subscribe(
            {
                currentUserData = it

                requireActivity().runOnUiThread(Runnable {
                    Glide.with(this)
                        .load(currentUserData?.profileimage)
                        .into(profile_image)
                })
            },{}

    )



        initNavigationBar(childFragment)

        initNavigationBar(childFragment)



        // 프로필 사진 클릭 시
        profile_image.setOnClickListener() {
            openGalley()
        }

        var firestore = FirebaseFirestore.getInstance()


    }

    private fun initNavigationBar(childFragment : FragmentManager) {
        mypage_menu_button.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.mypage_menu_feed -> {
                        val fragmentMypageFeed: Fragment = fragmentMypageFeed
                        childFragment.beginTransaction()
                            .replace(R.id.child_fragment_container, fragmentMypageFeed).addToBackStack(null).commit() // 중첩 fragment가 보이도록 투명색
                    }
                    R.id.mypage_menu_fan -> {
                        val fragmentMypageFan: Fragment = fragmentMypageFan
                        childFragment.beginTransaction()
                            .replace(R.id.child_fragment_container, fragmentMypageFan).addToBackStack(null).commit()

                    }
                    R.id.mypage_menu_my -> {
                        val fragmentMypageMy: Fragment = fragmentMypageMy
                        childFragment.beginTransaction()
                            .replace(R.id.child_fragment_container, fragmentMypageMy).addToBackStack(null).commit()
                    }
                }
                true
            }
            selectedItemId = R.id.mypage_menu_feed
        }
    }

    // 갤러리 열기
    private val OPEN_GALLERY = 1

    fun openGalley() {
        var intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI // 데이터에 url이 저장됌
        intent.type = "image/*"
        startActivityForResult(intent, OPEN_GALLERY)

    }

    // MAINACTIVITY에서 sub로 넘어갔다가 다시 main으로 넘어올 때 사용하는 기본 메소드
   @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OPEN_GALLERY){
                var currentImageUrl : Uri? = data?.data // data에 url 저장해서 가져옴옴
                // 이미지 로딩
                Glide.with(this)
                    .load(data!!.data)
                    .into(profile_image)

                val user = auth.currentUser

                // 현재 시간
                val time = Calendar.getInstance()

                // 프로필 파일 명
                val filename = "profile_" + user?.email.toString() + "_" + time.getTime().toString()  + ".jpg"


                //storage 객체 만들고 참조
                val storage: FirebaseStorage = FirebaseStorage.getInstance() //스토리지 인스턴스를 만들고,
                val storageRef: StorageReference = storage.getReference() //스토리지를 참조한다

                // storage에 파일 업로드
                val riversRef = storageRef.child("profile_img/$filename")
                val uploadTask = riversRef.putFile(currentImageUrl!!)

                // 새로운 프로필 이미지 저장
                uploadTask.addOnFailureListener { }.addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "프로필 이미지가 변경되었습니다.",
                        Toast.LENGTH_SHORT
                    ).show()

                    // 저장 성공 시 url 가져와서 저장하기
                    storageRef.child("profile_img/$filename").downloadUrl.addOnSuccessListener {
                        // 프로파일 이미지 저장
                        firestore.collection("User").document(user?.email.toString()).update("profileimage", it.toString())

                    }.addOnFailureListener {
                        //실패시
                    }
                }

               }
        }
    }

    companion object {

    }
}