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

abstract class AbstractRouteGroup {
    open var map: MutableMap<List<String>, AbstractRouteGroup> =  mutableMapOf()
    open fun addHandle(path:List<String>, abstractRouteGroup: AbstractRouteGroup) {
        RouteBase.map[path] = abstractRouteGroup
    }
    // 默认回复的接口 会根据message 通过反射获取对应方法调用
    open suspend fun reply(message: List<String>, subject: Contact) {
        if (message.isEmpty()) {
            subject.sendMessage(this.help(message))
            return
        }
        this.javaClass.kotlin.declaredFunctions.forEach {
//            print("${it.name},${annotation?.path}")
            this.map.forEach { (key, value) ->
                if (message.isNotEmpty() && message[0] in key) {
                    value.reply(message.subList(1, message.size),subject)
                    return
                }
            }
            val functionAnnotation = it.findAnnotation<RouteAnnotaion.FunctionPath>()
            if (functionAnnotation != null) {
                if (!functionAnnotation.path.isNullOrEmpty() && functionAnnotation.path.contains(message[0])) {
                    when (val msg = it.call(this, message)) {
                        is String -> subject.sendMessage(msg)
                        is Message -> subject.sendMessage(msg)
                        is File -> subject.sendImage(msg)
                        is BufferedImage -> subject.sendImage(msg)
                        is URL -> subject.sendImage(msg)
                        is InputStream -> subject.sendImage(msg)
                        is BufferedInputStream -> subject.sendImage(msg)
                        else -> {
                        }
                    }
                }
            }
        }
    }

    open fun help(message: List<String>): String {
        var s = ""
        this.javaClass.kotlin.declaredFunctions.forEachIndexed { index, kFunction ->
            val annotation = kFunction.findAnnotation<RouteAnnotaion.FunctionPath>()
            if (annotation?.path != null) {
                s += "- ${annotation.path.joinToString("/")} [${annotation.description}] \n"
            }
        }
        this.map.forEach { (key, value) ->
            s += "- ${key.joinToString("/")} [${value.getDescription()}]"
        }
        return s
    }

    open fun getDescription(): String {
        return ""
    }
}