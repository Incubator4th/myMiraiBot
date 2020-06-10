package config

import org.yaml.snakeyaml.Yaml
import java.io.InputStream


object Account{
    var qq:Long = 0
    var password:String = ""
    init {
        var yaml = Yaml()
         var inputStream = this.javaClass.classLoader.getResourceAsStream("config.yaml")
        var obj = yaml.load<Map<String,Any>>(inputStream)
        this.qq = obj["qq"].toString().toLong()
        this.password = obj["password"].toString()
    }
}

