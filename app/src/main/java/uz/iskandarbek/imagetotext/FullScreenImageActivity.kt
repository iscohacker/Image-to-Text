package uz.iskandarbek.imagetotext

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class FullScreenImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        val imageView: ImageView = findViewById(R.id.fullScreenImageView)

        val imageUri = intent.getStringExtra("IMAGE_URI")
        if (imageUri != null) {
            imageView.setImageURI(Uri.parse(imageUri))
        }
    }
}
