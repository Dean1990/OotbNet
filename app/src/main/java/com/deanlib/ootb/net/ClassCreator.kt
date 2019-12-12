package com.deanlib.ootb.net

import java.io.File

class ClassCreator {

    private var templateFile:File? = null
    private var params = arrayOf<String>()

    fun setTemplateFile(file:File){
        templateFile = file
    }

    fun setParameters(vararg params:String){
        this.params = params as Array<String>
    }
    fun create(templateFile:File,params: Array<out String>){

    }
}