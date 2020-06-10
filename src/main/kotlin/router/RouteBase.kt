package router

import annotaion.RoutePath
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.findAnnotation

open class RouteBase(val message: List<String>):AbstractRouteGroup {
    // 默认回复的接口 会根据message 通过反射获取对应方法调用
    override fun reply(): String {
        if (message.isEmpty()) {
            return this.help()
        }
        this.javaClass.kotlin.declaredFunctions.forEach {
            val annotation = it.findAnnotation<RoutePath>()
            print("${it.name},${annotation?.path}")
            if (annotation?.path.equals(message[0])) {
                return it.call(this) as String
            }
        }
        return "命令未找到"
    }

    @RoutePath(path = "help")
    override fun help(): String {
        var s = ""
        this.javaClass.kotlin.declaredFunctions.forEach {
            val annotation = it.findAnnotation<RoutePath>()
            if (annotation?.path != null) {
                s += "\t ${annotation.path} \n"
            }
        }
        return s
    }
}