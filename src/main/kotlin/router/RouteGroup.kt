package router

import annotaion.RoutePath

open class RouteGroup(message: List<String>) : RouteBase(message), AbstractRouteGroup {
    init {
    }

    @RoutePath(path = "test")
    fun test(): String {
        return "test"
    }

    @RoutePath(path = "sub")
    fun subPath(): String {
        if (message.size > 1) {
            return SubRouteGroup(message.subList(1, message.size)).reply()
        }
        return SubRouteGroup(listOf("help")).reply()
    }

    @RoutePath(path = "help")
    override fun help(): String {
        return super.help()
    }

}