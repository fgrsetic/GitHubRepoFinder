package com.franjo.github.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.franjo.github.domain.shared.CODE_PARAMETER
import com.franjo.github.domain.shared.REDIRECT_URI_CALLBACK
import com.franjo.github.presentation.databinding.ActivityBaseBinding
import com.franjo.github.presentation.features.login.LoginViewModel
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_base.*
import javax.inject.Inject

// This is a single activity application that uses the Navigation library.
// Content is displayed by Fragments
class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var loginViewModel: LoginViewModel

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_base)

        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(
            navController,
            appBarConfiguration
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return (Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
                || super.onSupportNavigateUp())
    }

    // Fetch the receiving intent when redirected from browser
    override fun onResume() {
        super.onResume()
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(REDIRECT_URI_CALLBACK)) {
            val code = uri.getQueryParameter(CODE_PARAMETER)
            if (code != null) {
                loginViewModel.accessToken(code)
            }
        }
    }
}