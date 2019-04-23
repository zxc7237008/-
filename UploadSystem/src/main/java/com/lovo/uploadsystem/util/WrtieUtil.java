package com.lovo.uploadsystem.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import com.lovo.uploadsystem.entity.JournalEntity;



public class WrtieUtil {

	
	/**
     * 写入TXT文件
     */
    public static void writeFile(JournalEntity journalEntity) {
        try {
            File writeName = new File("E:\\output.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("用户"+journalEntity.getName()+journalEntity.getIncident()+"  "+"时间为"+journalEntity.getData()+"\r\n"); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       
        
    }


}
