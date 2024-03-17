package org.freepixels.server;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class SiteHelpList {

    public static int GetHelpList(CommandContext<ServerCommandSource> context){
        // 获取玩家Source
        ServerCommandSource PlayerSource = context.getSource();


        Text HelpLinMessage = Text.literal("")
                .append(Text.literal("[Freepixels] 位置标注插件,帮助菜单:\n").formatted(Formatting.BLUE,Formatting.BOLD))
                .append(Text.literal("[Freepixels] /site help 帮助列表 \n").formatted(Formatting.WHITE))
                .append(Text.literal("[Freepixels] /site list 服务器地点列表 \n").formatted(Formatting.WHITE))
                .append(Text.literal("[Freepixels] /site add <名称> 将当前坐标添加为一个新的地点 \n").formatted(Formatting.WHITE))
                .append(Text.literal("[Freepixels] /site delete <名称> 通过名称删除地点 \n").formatted(Formatting.WHITE))
                .append(Text.literal("[Freepixels] /site search <名称> 通过名称搜索地点 \n").formatted(Formatting.WHITE))
                .append(Text.literal("\n"))
                .append(Text.literal("[Freepixels] 有问题请在QQ群内at服主!").formatted(Formatting.YELLOW,Formatting.BOLD));

        PlayerSource.sendFeedback(() ->HelpLinMessage, false);
        return 1;
    }

}
