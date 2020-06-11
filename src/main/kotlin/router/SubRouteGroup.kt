package router

import annotaion.RouteAnnotaion
import net.mamoe.mirai.contact.Contact

object SubRouteGroup: AbstractRouteGroup() {
    override suspend fun reply(message: List<String>, subject: Contact) {
        super.reply(message,subject)
    }

    @RouteAnnotaion.FunctionPath(path = arrayOf("help","帮助"))
    override fun help(message: List<String>): String {
        return super.help(message)
    }

}