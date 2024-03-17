package org.freepixels.server;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import org.freepixels.handler.LocationDataModelHandler;
import org.freepixels.model.LocationDataModel;

import java.util.Objects;

public class SiteDeleteClass {

    public static int SiteDelete(CommandContext<ServerCommandSource> context){
        // 获取要删除的地名
        String DeleteName = StringArgumentType.getString(context, "name");

        // 获取玩家Source
        ServerCommandSource PlayerSource = context.getSource();

        // 注册Db库
        LocationDataModelHandler NewLocationDataModelHandler = new LocationDataModelHandler();

        // 检查是否存在,或者指令是否错误
        if(Objects.equals(DeleteName, "")){
            PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 请输入正确的地点名称,错误内容: 搜索值 <" + DeleteName + "> 是非法的!", Style.EMPTY.withColor(Formatting.RED)), false);
            return 1;
        }

        // 先搜索是否存在
        LocationDataModel location = NewLocationDataModelHandler.getLocationByName(DeleteName);

        if(location == null){
            PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 未搜索到地点" + DeleteName + ",该地点不存在!",Style.EMPTY.withColor(Formatting.RED)), false);
            return 1;
        }

        // 获取玩家的UUID
        String PlayerUUID = PlayerSource.getPlayer().getUuidAsString();

        // 检查是否是删除自己的
        if(!Objects.equals(location.getHolderuuid(), PlayerUUID)){
            // 检查是不是OP权限
            if(!PlayerSource.hasPermissionLevel(4)){
                PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 您只能删除自己创建的地点! 此地点的用有人是:" + location.getHolder(), Style.EMPTY.withColor(Formatting.RED)), false);
                return 1;
            }
        }

        // 执行删除
        if(!NewLocationDataModelHandler.deleteLocation(DeleteName)){
            PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 添加失败,无法更新存储记录!" + location.getHolder(), Style.EMPTY.withColor(Formatting.RED)), false);
            return 1;
        }

        PlayerSource.sendFeedback(() -> CommonMethod.MakeReturnMessage("[Freepixels] 删除地点成功!", Style.EMPTY.withColor(Formatting.GREEN)), false);
        return 1;
    }

}
