package com.example.projectprepare1.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *   file->setting ->editor->file and code templtes ->kotlin class ->粘贴
    #if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}
    #end
    /**
    2 * Copyright (C), 2015-${YEAR}, 雨女无瓜小组
    3 * FileName: ${NAME}
    4 * Author: ${USER}
    5 * Date: ${DATE} ${TIME}
    6 * Description: ${DESCRIPTION}
    7 * Notes：
    9 * Time：${YEAR}年${MONTH}月${DAY}日${HOUR}时
    9*/
    #parse("File Header.java")
    class ${NAME} {
    }

 */

/**
* Description: 创建数据库类
*/

@Entity(tableName = "word_table")
data class Item(@PrimaryKey @ColumnInfo(name = "word") val word: String)