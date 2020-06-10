package myMiraiBot

import config.Account
import net.mamoe.mirai.Bot
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.event.subscribeAlways
import net.mamoe.mirai.join
import net.mamoe.mirai.message.MessageEvent
import net.mamoe.mirai.message.data.content
import router.RouteGroup

suspend fun main() {
    val miraiBot = Bot(Account.qq,Account.password){
        fileBasedDeviceInfo("device.json")
    }.alsoLogin()
    directlySubscribe(miraiBot)
    miraiBot.join()
}

suspend fun  directlySubscribe(bot:Bot) {
    bot.subscribeAlways<MessageEvent> {
        this.reply(RouteGroup(this.message.content.split(" ")).reply())
    }
}