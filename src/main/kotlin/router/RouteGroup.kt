package router

import annotaion.RouteAnnotaion
import net.mamoe.mirai.contact.Contact

object RouteGroup : AbstractRouteGroup() {

    override var map: MutableMap<List<String>, AbstractRouteGroup> =
        mutableMapOf(listOf<String>("sub","子目录") to SubRouteGroup)

    @RouteAnnotaion.FunctionPath(path = arrayOf("test"), description = "返回test")
    fun test(message: List<String>): String {
        return "test"
    }


    override suspend fun reply(message: List<String>, subject: Contact) {
        super.reply(message,subject)
    }

    @RouteAnnotaion.FunctionPath(path = arrayOf("help", "帮助"), description = "显示帮助信息")
    override fun help(message: List<String>): String {
        return super.help(message)
    }

    override fun getDescription(): String {
        return "测试类型group的子功能"
    }

}