package org.freepixels.server;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;

import java.util.Objects;
import org.freepixels.handler.LocationDataModelHandler;

public class SiteAddClass {

    public static int SiteAdd(CommandContext<ServerCommandSource> context){
        // 获取要添加的地名
        String AddPosNameOld = StringArgumentType.getString(context, "name");

        // 去掉空格
        String AddPosName = AddPosNameOld.replace(" ", "_");

        // 获取玩家Source
        ServerCommandSource PlayerSource = context.getSource();

        // 注册Db库
        LocationDataModelHandler NewLocationDataModelHandler = new LocationDataModelHandler();

        // 检查是否存在,或者指令是否错误
        if(Objects.equals(AddPosName, "")){
            PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 地点添加失败,请输入正确的名称!",Style.EMPTY.withColor(Formatting.RED)), false);
            return 1;
        }

        if(NewLocationDataModelHandler.getLocationByName(AddPosName) != null){
            PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 地点已存在!",Style.EMPTY.withColor(Formatting.RED)), false);
            return 1;
        }

        // 获取玩家位置
        int PlayerPosX = (int) Math.floor(PlayerSource.getPosition().x);
        int PlayerPosY = (int) Math.floor(PlayerSource.getPosition().y);
        int PlayerPosZ = (int) Math.floor(PlayerSource.getPosition().z);

        // 获取玩家所在世界的名称
        String PlayerWorldName = PlayerSource.getWorld().getRegistryKey().getValue().toString();

        // 获取玩家名称和UUID
        String PlayerName = Objects.requireNonNull(PlayerSource.getPlayer()).getName().getString();
        String PlayerUUID = PlayerSource.getPlayer().getUuidAsString();

        // 执行写入
        if(!NewLocationDataModelHandler.addLocation(AddPosName,PlayerWorldName,PlayerName,PlayerUUID, CommonMethod.GetServerTime(),PlayerPosX,PlayerPosY,PlayerPosZ)){
            PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 添加失败,无法更新存储记录!",Style.EMPTY.withColor(Formatting.RED)), false);
            return 1;
        }

        PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 地点: " + AddPosName + " 添加成功!",Style.EMPTY.withColor(Formatting.GREEN)), false);
        return 1;
    }



}
