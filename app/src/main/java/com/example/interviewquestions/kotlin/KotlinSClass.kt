package com.example.interviewquestions.kotlin

import java.util.*

class KotlinSClass {
    fun getList():ArrayList<String>{
        for (i in 1..200){
            strList[i] = i?.toString()
        }
        return strList
    }

    fun getStr():String{
        var str: String? =null
        for (i in strList){
            str += i
        }
        return str?:""
    }


    companion object{
        var strList:ArrayList<String> = ArrayList()

        var instance = KotlinSClass()
    }
}