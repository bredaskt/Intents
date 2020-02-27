package com.bbreda.intents.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bbreda.intents.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListeners()
    }

    private fun setupListeners() {
        btShareOptions.setOnClickListener {
            shareOptions()
        }

        btShareWhatsapp.setOnClickListener {
            shareWhatsapp()
        }

        btShareEmail.setOnClickListener {
            shareEmail()
        }
    }

    private fun shareOptions() {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_TEXT, TEXT_FOR_SHARE)
        startActivity(Intent.createChooser(share, "Share using"))
    }

    private fun shareWhatsapp() {
        val smsNumber = PHONE_NUMBER_FOR_DIRECT_SHARE_CONTACT

        try {
            val sendIntent = Intent("android.intent.action.MAIN")
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, TEXT_FOR_SHARE)
            sendIntent.putExtra(
                "jid",
                smsNumber + "@s.whatsapp.net"
            ) //phone number without "+" prefix
            sendIntent.setPackage("com.whatsapp")
            startActivity(sendIntent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareEmail() {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(EMAIL_RECIPIENT))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, EMAIL_SUBJECT)
        mIntent.putExtra(Intent.EXTRA_TEXT, EMAIL_MESSAGE)
        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val TEXT_FOR_SHARE = "Insert text for share"
        private const val PHONE_NUMBER_FOR_DIRECT_SHARE_CONTACT = "5519981028785"
        private const val EMAIL_RECIPIENT = "bredaskate@gmail.com"
        private const val EMAIL_SUBJECT = "Título do email"
        private const val EMAIL_MESSAGE = "Insert text for share"
    }
}
