package org.freepixels.server;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonMethod {
    public static String GetServerTime(){
        // 获取当前的日期时间
        LocalDateTime now = LocalDateTime.now();
        // 定义日期时间的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化当前日期时间
        return now.format(formatter);
    }


    public static Text MakeReturnMessage(String Message, Style MessageType){
        return Text.literal(Message).setStyle(MessageType);
    }
}
