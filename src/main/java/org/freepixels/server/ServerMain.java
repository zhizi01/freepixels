package org.freepixels.server;

import net.fabricmc.api.DedicatedServerModInitializer ;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import org.freepixels.server.SiteListClass;
import org.freepixels.server.SiteAddClass;
import org.freepixels.server.SiteDeleteClass;
import org.freepixels.server.SiteHelpList;
import org.freepixels.server.SiteSearchClass;

public class ServerMain implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        // 注册服务器指令

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            registerCommands(dispatcher);
        });

    }

    private void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("site")
                .requires(source -> source.hasPermissionLevel(0)) // 注册0级指令允许所有玩家使用
                .then(CommandManager.literal("list") // 注册指令 /site list 获取地点列表
                        .executes(SiteListClass::GetSiteList))
                .then(CommandManager.literal("help") // 注册指令 /site help 获取帮助列表
                        .executes(SiteHelpList::GetHelpList))
                .then(CommandManager.literal("search") // 注册指令 /site search <Name> 通过名称获取指定的地点
                        .then(CommandManager.argument("name", StringArgumentType.greedyString())
                                .executes(SiteSearchClass::SearchByName)))
                .then(CommandManager.literal("add") // 注册指令 /site add <Name> 添加一个新的地点
                        .then(CommandManager.argument("name", StringArgumentType.greedyString())
                                .executes(SiteAddClass::SiteAdd)))
                .then(CommandManager.literal("delete") // 注册指令 /site delete <Name> 通过名称删除一个地点(玩家只能删除自己的,OP可以删除任意的)
                        .then(CommandManager.argument("name", StringArgumentType.greedyString())
                                .executes(SiteDeleteClass::SiteDelete))));
    }

}
