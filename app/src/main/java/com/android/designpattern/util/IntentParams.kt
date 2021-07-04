package com.android.designpattern.util


class IntentParams{
    var key : String? = ""
    var value : String? = ""
    constructor(){}
    constructor(key : String , value : String){
        this.key = key
        this.value = value
    }
}