import org.json.JSONObject
import java.io.File
import java.net.URLClassLoader

import plugins.*

object Plugins{
    var pluginsMap = mutableMapOf<String, PluginBase>()
    var pluginsList = mutableListOf<PluginBase>()

    fun loadPlugins(){
        Status()
        Help()
        ContextTest()
    }

    fun initPlugin(plugin: PluginBase){
        pluginsList.add(plugin)

        for (name in plugin.names){
            if (name !in pluginsMap){
                pluginsMap[name] = plugin
            }
        }
        println("Init plugin \"${plugin.names[0]}\"")
    }
}

abstract class PluginBase{
    abstract val names: MutableList<String>
    abstract val desc: String
    open val level: Int = 1
    open val hidden: Boolean = false
    abstract fun main(msg: Utils.Msg)
}

abstract class ContextBase{
    abstract fun main(msg: Utils.Msg, args: JSONObject)
}