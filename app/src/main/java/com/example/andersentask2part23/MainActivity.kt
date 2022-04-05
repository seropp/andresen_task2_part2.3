package com.example.andersentask2part23

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ShareCompat
import com.example.andersentask2part23.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnWebsite.setOnClickListener {
            val uri = Uri.parse(binding.etWebsite.text.toString())
            val intent = Intent(Intent.ACTION_VIEW, uri)

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent);
            } else {
                Log.d("ImplicitIntents", "Can't handle this!");
            }
        }

        binding.btnLocation.setOnClickListener {
            val addressUri  = Uri.parse("geo:0,0?q=${binding.etLocation.text.toString()}")
            val intent = Intent(Intent.ACTION_VIEW, addressUri )
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent);
            } else {
                Log.d("ImplicitIntents", "Can't handle this!");
            }
        }

        binding.btnShare.setOnClickListener {
            val txt  = binding.etShare.text.toString()
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share text with")
                .setText(txt)
                .startChooser();
        }

        binding.btnCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            
            if(intent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(intent, 42)
            }

        }

    }
}