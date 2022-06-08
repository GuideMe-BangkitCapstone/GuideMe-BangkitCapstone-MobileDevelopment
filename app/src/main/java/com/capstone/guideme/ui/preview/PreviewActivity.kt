package com.capstone.guideme.ui.preview

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.guideme.databinding.ActivityPreviewBinding
import com.capstone.guideme.ui.camera.CameraActivity
import com.capstone.guideme.ui.detail.DetailActivity
import com.capstone.guideme.utils.UserPreference
import com.capstone.guideme.utils.ViewModelFactory
import com.capstone.guideme.utils.reduceFileImage
import com.capstone.guideme.utils.showLoading
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PreviewActivity : AppCompatActivity() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private lateinit var previewViewModel: PreviewViewModel
    private lateinit var binding: ActivityPreviewBinding
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startCameraX()
        binding.btnScan.setOnClickListener { uploadImage() }
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = myFile
            val result =  BitmapFactory.decodeFile(myFile.path)

//          FOR EMULATOR USER !
//            val result = rotateBitmap(
//                BitmapFactory.decodeFile(getFile?.path),
//                isBackCamera
//            )

            binding.imageView.setImageBitmap(result)
        }
    }

    private fun uploadImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image",
                file.name,
                requestImageFile
            )

            setupViewModel()

            previewViewModel.getUser().observe(this) {
                previewViewModel.getDetection(it.token, imageMultipart)
            }

            previewViewModel.isLoading.observe(this) {
                showLoading(it, binding.viewLoading)
            }

            previewViewModel.response.observe(this) {
                if (it.error == true) {
                    Toast.makeText(
                        this@PreviewActivity,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Intent(this@PreviewActivity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_NAME, it.placeName)
                        startActivity(this)
                    }
                    finish()
                }
            }
        }
    }

    private fun setupViewModel() {
        previewViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[PreviewViewModel::class.java]
    }

    companion object {
        const val CAMERA_X_RESULT = 200
    }

}