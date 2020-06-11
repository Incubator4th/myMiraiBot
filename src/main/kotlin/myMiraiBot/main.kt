package myMiraiBot

import config.Account
import net.mamoe.mirai.Bot
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.event.subscribeAlways
import net.mamoe.mirai.join
import net.mamoe.mirai.message.MessageEvent
import net.mamoe.mirai.message.data.content
import router.RouteBase
import router.RouteGroup

suspend fun main() {
    val miraiBot = Bot(Account.qq, Account.password) {
        fileBasedDeviceInfo("device.json")
    }.alsoLogin()
    RouteBase.addHandle(listOf("group"), RouteGroup)
    directlySubscribe(miraiBot)
    miraiBot.join()
}

suspend fun directlySubscribe(bot: Bot) {
    bot.subscribeAlways<MessageEvent> {
        RouteBase.reply(this.message.content.split(" "), this.subject)
    }
}