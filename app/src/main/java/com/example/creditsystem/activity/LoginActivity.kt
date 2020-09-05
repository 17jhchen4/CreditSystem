package com.example.creditsystem.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.Explode
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.creditsystem.R
import com.example.creditsystem.tools.MyApplication
import com.example.creditsystem.tools.SnackBarUtil
import com.example.creditsystem.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

/**
@author chenjinhui
@date : 2020/9/3 22:23
@description: 带动画效果的登录activity
 */
class LoginActivity : AppCompatActivity(){

    private val mContext = MyApplication.context
    private val LENGTH_MAX = 16

    private lateinit var mViewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        initListener()

    }

    private fun initListener() {
        et_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (et_username.text != null) {
                    if (et_username.text.length > LENGTH_MAX) {
                        tl_username.error = mContext.getString(R.string.input_username_over_max)
                    } else {
                        if (et_username.text.isEmpty()) {
                            tl_username.error = mContext.getString(R.string.input_username_empty)
                        } else {
                            tl_username.isErrorEnabled = false
                        }
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


        fab.setOnClickListener {
            window.exitTransition = null
            window.enterTransition = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val options =
                    ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.transitionName)
                startActivity(Intent(this, RegisterActivity::class.java), options.toBundle())
            } else {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
        bt_go.setOnClickListener {
            if (tl_username.isErrorEnabled || tl_password.isErrorEnabled) {
                SnackBarUtil.makeSnackBar(it, mContext.getString(R.string.input_username_or_password))
            } else {
//                SnackBarUtil.makeSnackBar(it, "验证通过!")
//                enterMainActivity()
            }
        }
    }

    private fun enterMainActivity() {
        val explode = Explode()
        explode.duration = 500

        window.exitTransition = explode
        window.enterTransition = explode
        val oc2: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(this)
        val i2 = Intent(this, MainActivity::class.java)
        startActivity(i2, oc2.toBundle())
        finish()
    }

}