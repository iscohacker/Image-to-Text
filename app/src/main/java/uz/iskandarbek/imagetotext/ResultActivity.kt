package uz.iskandarbek.imagetotext

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val imageView: ImageView = findViewById(R.id.selectedImageView)
        val textView: TextView = findViewById(R.id.extractedTextView)
        val copyTextButton: TextView = findViewById(R.id.copy)

        val imageUri = intent.getStringExtra("IMAGE_URI")
        val extractedText = intent.getStringExtra("EXTRACTED_TEXT")

        imageUri?.let {

            imageView.setImageURI(Uri.parse(it))
            imageView.setOnClickListener {
                // Rasmni to'liq ekranda ko'rsatish uchun yangi Activityni ochamiz
                val fullScreenIntent = Intent(this, FullScreenImageActivity::class.java).apply {
                    putExtra("IMAGE_URI", imageUri)
                }
                startActivity(fullScreenIntent)
            }
        }

        if (extractedText.isNullOrEmpty()) {
            textView.text = "Rasmda text mavjud emas"
        } else {
            textView.text = extractedText
        }

        copyTextButton.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = android.content.ClipData.newPlainText("Extracted Text", textView.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Matn nusxalandi", Toast.LENGTH_SHORT).show()
        }
    }
}
