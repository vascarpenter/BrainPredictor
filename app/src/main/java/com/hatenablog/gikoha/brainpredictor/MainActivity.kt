/*
 * Copyright (c) gikoha 2018.
 * このソフトウェアは、 Apache 2.0ライセンスで配布されている製作物が含まれています。
 * This software includes the work that is distributed in the Apache License 2.0
 *
 */

package com.hatenablog.gikoha.brainpredictor

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val risk_stroke = arrayOf(0, 2, 0, 0, 0, 1, 1, 1, -1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0)
    val risk_LVO = arrayOf(0, 0, 0, 1, 0, 0, -3, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 1, 1, 3, 0)
    val risk_ICH = arrayOf(0, 0, -2, 0, -2, 1, 1, 0, 0, 2, 0, 0, 1, 1, -2, 1, 1, 0, 0, 2, 0)
    val risk_SAH = arrayOf(-2, 2, 0, 0, 0, 0, 4, 0, 0, -2, -2, 1, 0, 0, 0, 2, 0, 0, 0, 0, -2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            // checkbox の集計
            var s_stroke = 0
            var s_LVO = 0
            var s_ICH = 0
            var s_SAH = 0

            for (i in 1..21) {
                var cbid: Int = getResources().getIdentifier("checkBox" + i.toString(), "id", getPackageName())
                val cb: CheckBox = findViewById(cbid)

                if (cb.isChecked()) {
                    s_stroke += risk_stroke[i - 1]
                    s_LVO += risk_LVO[i - 1]
                    s_ICH += risk_ICH[i - 1]
                    s_SAH += risk_SAH[i - 1]
                }
            }

            var str: String = ""

            when {
                s_stroke <= 0 -> str = "14%"
                s_stroke == 1 -> str = "36%"
                s_stroke == 2 -> str = "61%"
                s_stroke == 3 -> str = "71%"
                s_stroke >= 4 -> str = "88%"
            }
            p_stroke.text = str

            when {
                s_LVO <= -1 -> str = "0%"
                s_LVO <= 3 -> str = "4%"
                s_LVO <= 6 -> str = "18%"
                s_LVO == 7 -> str = "49%"
                s_LVO >= 8 -> str = "79%"
            }
            p_LVO.text = str

            when {
                s_ICH <= -2 -> str = "3%"
                s_ICH <= 2 -> str = "8%"
                s_ICH <= 4 -> str = "23%"
                s_ICH <= 6 -> str = "42%"
                s_ICH >= 7 -> str = "65%"
            }
            p_ICH.text = str

            when {
                s_SAH <= -3 -> str = "0%"
                s_SAH <= 2 -> str = "1%"
                s_SAH <= 4 -> str = "10%"
                s_SAH <= 6 -> str = "30%"
                s_SAH >= 7 -> str = "89%"
            }
            p_SAH.text = str
        }
    }
}
