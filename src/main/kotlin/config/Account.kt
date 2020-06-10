package config

import org.yaml.snakeyaml.Yaml
import java.io.InputStream


object Account{
    var qq:Long = 0
    var password:String = ""
    init {
        val yaml = Yaml()
        val inputStream = this.javaClass.classLoader.getResourceAsStream("config.yaml")
        val obj = yaml.load<Map<String,Any>>(inputStream)
        this.qq = obj["qq"].toString().toLong()
        this.password = obj["password"].toString()
    }
}

