package router

import annotaion.RouteAnnotaion
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.message.data.Message
import net.mamoe.mirai.message.sendImage
import java.awt.image.BufferedImage
import java.io.BufferedInputStream
import java.io.File
import java.io.InputStream
import java.net.URL
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.findAnnotation

object RouteBase : AbstractRouteGroup() {


    @RouteAnnotaion.FunctionPath(path = arrayOf("help","帮助"),description = "显示帮助信息")
    override fun help(message: List<String>): String {
        return super.help(message)
    }
    @RouteAnnotaion.FunctionPath(path = arrayOf("info","信息"),description = "查看机器人信息")
    fun info(message: List<String>):String {
        return "暂时还没有信息"
    }
}