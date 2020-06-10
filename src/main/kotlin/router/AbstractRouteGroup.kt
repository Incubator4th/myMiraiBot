package router

interface AbstractRouteGroup {
    fun reply():String
    fun help():String
}