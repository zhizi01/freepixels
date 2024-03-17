package org.freepixels.server;

import com.google.gson.Gson;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import org.freepixels.handler.LocationDataModelHandler;
import org.freepixels.model.LocationDataModel;
import java.util.Objects;

public class SiteSearchClass {

    public static int SearchByName(CommandContext<ServerCommandSource> context){
        // 获取要添加的地名
        String SearchPosName = StringArgumentType.getString(context, "name");

        // 获取玩家Source
        ServerCommandSource PlayerSource = context.getSource();

        // 注册Db库
        LocationDataModelHandler NewLocationDataModelHandler = new LocationDataModelHandler();

        // 检查是否存在,或者指令是否错误
        if(Objects.equals(SearchPosName, "")){
            PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 请输入正确的地点名称,错误内容: 搜索值 <" + SearchPosName + "> 是非法的!", Style.EMPTY.withColor(Formatting.RED)), false);
            return 1;
        }

        LocationDataModel location = NewLocationDataModelHandler.getLocationByName(SearchPosName);

        if(location == null){
            PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 未搜索到地点" + SearchPosName + ",该地点不存在!",Style.EMPTY.withColor(Formatting.RED)), false);
            return 1;
        }

        String highlightCommand = String.format("/highlightWaypoint %d %d %d",location.getPosx(), location.getPosy(), location.getPosz());

        MutableText PosListMessage = Text.literal("")
                .append(Text.literal("[Freepixels] 地点: " + SearchPosName + " 搜索结果: \n").formatted(Formatting.BLUE,Formatting.BOLD));

        PosListMessage.append(Text.literal("地点: " + location.getName()).formatted(Formatting.YELLOW))
                .append(Text.literal(" 世界: " + location.getDimension()).formatted(Formatting.GREEN))
                .append(Text.literal(" 坐标: ")
                        .append(Text.literal("X: " + location.getPosx() + " Y: " + location.getPosy() + " Z: " + location.getPosz())
                                .styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, highlightCommand))
                                        .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("点击高亮坐标(Xaero's Minimap)"))))
                        ).formatted(Formatting.RED,Formatting.BOLD))
                .append(Text.literal(" 标记玩家: " + location.getHolder()).formatted(Formatting.AQUA))
                .append(Text.literal(" 标记时间: " + location.getTime()).formatted(Formatting.GOLD))
                .append(Text.literal(" \n"));

        PlayerSource.sendFeedback(() ->PosListMessage, false);
        return 1;
    }

}
