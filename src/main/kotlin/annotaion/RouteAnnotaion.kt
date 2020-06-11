package annotaion

class RouteAnnotaion{
    @Repeatable
    annotation class FunctionPath(val path:Array<String>, val description:String="") {
    }

}
