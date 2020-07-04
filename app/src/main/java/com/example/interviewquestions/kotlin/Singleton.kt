package com.example.interviewquestions.kotlin

//kotlin实现
class Singleton private constructor() {
    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = Singleton()
    }

    fun  getId():String{
        return "id"
    }
}