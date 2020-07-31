package com.franjo.github.data.repository

import android.app.Application
import android.content.Intent
import android.net.Uri
import com.franjo.github.data.network.service.AUTHORIZE_USER_URL
import com.franjo.github.domain.repository.ILoginRepository
import com.franjo.github.domain.shared.CLIENT_ID
import com.franjo.github.domain.shared.REDIRECT_URI_CALLBACK
import com.franjo.github.domain.shared.SCOPE
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val app: Application
) : ILoginRepository {


    // Redirect users to Requests GitHub access and get authorization token
    override fun login() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                AUTHORIZE_USER_URL
                        + "?client_id=" + CLIENT_ID
                        + "&scope=" + SCOPE
                        + "&redirect_uri=" + REDIRECT_URI_CALLBACK
            )
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        app.startActivity(intent)
    }


}
