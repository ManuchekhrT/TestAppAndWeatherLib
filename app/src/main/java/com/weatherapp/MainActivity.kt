package com.weatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.weatherapp.extensions.formatDate
import com.weatherapp.extensions.hide
import com.weatherapp.extensions.hideKeyboard
import com.weatherapp.extensions.show
import com.weatherlib.WeatherApp
import com.weatherlib.networking.model.WeatherModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {


    private lateinit var app: WeatherApp
    private lateinit var loadingPb: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = WeatherApp()

        loadingPb = findViewById(R.id.loading_pb)
        val searchEdt = findViewById<EditText>(R.id.search_edt)
        findViewById<Button>(R.id.submit_btn).apply {
            setOnClickListener {
                val searchQuery = searchEdt.text.toString()
                if (searchQuery.isBlank()) searchEdt.error =
                    resources.getString(R.string.error_empty_field)
                else {
                    hideKeyboard(this@MainActivity, this)
                    getCurrentWeather(searchQuery)
                }
            }
        }
    }

    private fun getCurrentWeather(query: String) {
        app.weatherProvider.apply {
            getCurrentWeather(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingPb.show() }
                .subscribe({
                    loadingPb.hide()
                    displayWeather(it)
                }, {
                    loadingPb.hide()
                    Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                })
        }
    }

    private fun displayWeather(it: WeatherModel?) {
        if (it != null) {
            val weather = it.consolidatedWeather.first()
            val builder = AlertDialog.Builder(this)

            builder.setTitle(it.title)
            builder.setMessage(
                "Condition: " + weather.weatherStateName + "\n" +
                        "Temperature: " + weather.temp + "°C\n" +
                        "Air Pressure: " + weather.airPressure + "mbar\n" +
                        "Humidity: " + weather.humidity + "%\n" +
                        "Predictability: " + weather.predictability + "%\n" +
                        "Current date: " + formatDate(weather.applicable_date) + "\n"
            )
            builder.setIcon(android.R.drawable.ic_dialog_map)

            builder.setPositiveButton("Ок") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

}