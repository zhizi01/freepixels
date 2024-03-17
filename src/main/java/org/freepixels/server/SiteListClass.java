package org.freepixels.server;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.freepixels.handler.LocationDataModelHandler;
import org.freepixels.model.LocationDataModel;

import java.util.ArrayList;


public class SiteListClass {

    public static int GetSiteList(CommandContext<ServerCommandSource> context){
        // 获取玩家Source
        ServerCommandSource PlayerSource = context.getSource();

        // 注册Db库
        LocationDataModelHandler NewLocationDataModelHandler = new LocationDataModelHandler();

        // 获取地点列表
        ArrayList<LocationDataModel> locationList = NewLocationDataModelHandler.getLocationList();

        MutableText PosListMessage = Text.literal("")
                .append(Text.literal("[Freepixels] 已标注的地点列表: \n").formatted(Formatting.BLUE,Formatting.BOLD));

        int ListNumberID = 0;

        for (LocationDataModel location : locationList) {
            // 递增
            ListNumberID++;
            // highlightCommand 是小地图高亮
            String highlightCommand = String.format("/highlightWaypoint %d %d %d",location.getPosx(), location.getPosy(), location.getPosz());
            // 添加消息
            PosListMessage.append(Text.literal("[#" + String.valueOf(ListNumberID) + "] 地点: " + location.getName()).formatted(Formatting.YELLOW))
                .append(Text.literal(" 世界: " + location.getDimension()).formatted(Formatting.GREEN))
                .append(Text.literal(" 坐标: ")
                .append(Text.literal("X: " + location.getPosx() + " Y: " + location.getPosy() + " Z: " + location.getPosz())
                        .styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, highlightCommand))
                                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("点击高亮坐标(Xaero's Minimap)"))))
                ).formatted(Formatting.RED,Formatting.BOLD))
                .append(Text.literal(" 标记玩家: " + location.getHolder()).formatted(Formatting.AQUA))
                .append(Text.literal(" 标记时间: " + location.getTime()).formatted(Formatting.GOLD))
                .append(Text.literal(" \n"));
        }

        PlayerSource.sendFeedback(() ->PosListMessage, false);
        return 1;
    }

}
