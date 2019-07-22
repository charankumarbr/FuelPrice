package `in`.charan.fuelprice.ui.splash

import `in`.charan.fuelprice.BuildConfig
import `in`.charan.fuelprice.FuelPriceApplication
import `in`.charan.fuelprice.R
import `in`.charan.fuelprice.di.splash.DaggerSplashComponent
import `in`.charan.fuelprice.util.AppConstants
import `in`.charan.fuelprice.util.Util
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        init()
    }

    private fun init() {
        asTvVersion.text = "v ${BuildConfig.VERSION_NAME}"

        val appComponent = (application as FuelPriceApplication).getAppComponent()
        val splashComponent = DaggerSplashComponent.builder()
            .appComponent(appComponent)
            .build()
        splashComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)

        Thread(Runnable {
            try {
                Thread.sleep(AppConstants.SPLASH_DELAY)

            } catch (exception: InterruptedException) {

            }
            handler.sendEmptyMessage(200)
        }).start()
    }

    private val handler = Handler {
        return@Handler if (it.what == 200) {
            checkAndProceed()
            true
        } else {
            false
        }
    }

    private fun checkAndProceed() {
        if (viewModel.isConnected(this)) {
            if (viewModel.isStateSet()) {
                startActivity(viewModel.getHomeIntent(this))

            } else {
                startActivity(viewModel.getChooseStateIntent(this))
            }
            finish()

        } else {
            Util.displayToast("Please check internet", applicationContext)
        }
    }
}
