package com.example.m_13weather_app_final

object Validator {

    fun validateInput(amount: Int, desc:String):Boolean{

        return!(amount <=0 || desc.isEmpty())
    }
}