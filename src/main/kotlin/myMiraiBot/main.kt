package myMiraiBot

import config.Account
import net.mamoe.mirai.Bot
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.join

suspend fun main() {
    val miraiBot = Bot(Account.qq,Account.password){
        fileBasedDeviceInfo("device.json")
    }.alsoLogin()
    miraiBot.subscribeMessages {
        "test" reply "test"
        "help" reply "帮助"
        "print" reply miraiBot.friends.toString()
        "s".reply {
            "a" + this.senderName
        }
    }
    miraiBot.join()
}