/**
 * Copyright (C), 2015-2019
 * FileName: AASubtitle
 * Author: AnAn
 * Date: 2019-08-30 11:29
 * Description:
 * History:
 */
package com.aachartmodel.aainfographics.AAInfographicsLib.AAOptionsModel

import com.example.chartcorekotlin.AAChartConfiger.AAChartVerticalAlignType

class AASubtitle {
    private var text: String? = null
    private var style: AAStyle? = null
    private var align: String? = null
    private var verticalAlign: String? = null
    private var x: Float? = null
    private var y: Float? = null
    private var userHTML: Boolean? = null

    fun text(prop: String?): AASubtitle {
        text = prop
        return this
    }

    fun style(prop: AAStyle): AASubtitle {
        style = prop
        return this
    }

    fun align(prop: String?): AASubtitle {
        align = prop
        return this
    }

    fun verticalAlign(prop: AAChartVerticalAlignType): AASubtitle {
        verticalAlign = prop.toString()
        return this
    }

    fun x(prop: Float?): AASubtitle {
        x = prop
        return this
    }

    fun y(prop: Float?): AASubtitle {
        y = prop
        return this
    }

    fun userHTML(prop: Boolean?): AASubtitle {
        userHTML = prop
        return this
    }
}
