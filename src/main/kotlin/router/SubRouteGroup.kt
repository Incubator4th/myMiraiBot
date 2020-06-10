package router

import annotaion.RoutePath

class SubRouteGroup(message: List<String>): RouteBase(message),AbstractRouteGroup {
    override fun reply(): String {
        return super.reply()
    }

    @RoutePath(path = "help")
    override fun help(): String {
        return super.help()
    }

}