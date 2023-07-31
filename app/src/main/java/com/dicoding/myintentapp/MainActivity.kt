package com.dicoding.myintentapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil : $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveAct: Button = findViewById(R.id.buttonMoveActivity)
        btnMoveAct.setOnClickListener(this)

        val btnMoveActData: Button = findViewById(R.id.buttonMoveActivityData)
        btnMoveActData.setOnClickListener(this)

        val btnMoveActObject: Button = findViewById(R.id.buttonMoveActivityObject)
        btnMoveActObject.setOnClickListener(this)

        val btnDialNumber: Button = findViewById(R.id.buttonDialNumber)
        btnDialNumber.setOnClickListener(this)

        val btnMoveForResult: Button = findViewById(R.id.btn_move_for_result)
        btnMoveForResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonMoveActivity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.buttonMoveActivityData -> {
                val moveDataIntent = Intent(this@MainActivity, MoveActivityData::class.java)
                moveDataIntent.putExtra(MoveActivityData.EXTRA_NAME, "Engge")
                moveDataIntent.putExtra(MoveActivityData.EXTRA_AGE, 20)
                startActivity(moveDataIntent)
            }

            R.id.buttonMoveActivityObject -> {
                val person = Person("Engge", 20, "nitsm@gmail.com", "Bandung")
                val moveObjIntent = Intent(this@MainActivity, MoveActivityObject::class.java)
                moveObjIntent.putExtra(MoveActivityObject.EXTRA_PERSON, person)
                startActivity(moveObjIntent)
            }

            R.id.buttonDialNumber -> {
                val phoneNumber = "082284849090"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_for_result -> {
                val moveforResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveforResultIntent)
            }
        }
    }
}