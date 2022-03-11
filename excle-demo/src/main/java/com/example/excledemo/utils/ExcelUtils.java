package com.example.excledemo.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.example.excledemo.exception.OperationException;
import com.example.excledemo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Yang Jian
 * @time: 2022/3/11 10:27
 */
public class ExcelUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    private static List<List<Object>> lineList = new ArrayList<>();

    /**
     * excel 导出工具类
     *
     * @param response
     * @param fileName    文件名
     * @param projects    对象集合
     * @param columnNames 导出的excel中的列名
     * @param keys        对应的是对象中的字段名字
     * @throws IOException
     */
    public static void export(HttpServletResponse response, String fileName, List<?> projects, String[] columnNames, String[] keys) throws IOException {

        ExcelWriter bigWriter = ExcelUtil.getBigWriter();

        for (int i = 0; i < columnNames.length; i++) {
            bigWriter.addHeaderAlias(columnNames[i], keys[i]);
            bigWriter.setColumnWidth(i, 20);
        }
        // 一次性写出内容，使用默认样式，强制输出标题
        bigWriter.write(projects, true);
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        bigWriter.flush(out, true);
        // 关闭writer，释放内存
        bigWriter.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    /**
     * excel导入工具类
     *
     * @param file       文件
     * @param columNames 列对应的字段名
     * @return 返回数据集合
     * @throws OperationException
     * @throws IOException
     */
    public static List<Map<String, Object>> leading(MultipartFile file, String[] columNames) throws OperationException, IOException {
        String fileName = file.getOriginalFilename();
        isCorrectParam(file, fileName);

        //读取数据
        ExcelUtil.readBySax(file.getInputStream(), 0, createRowHandler());
        //去除excel中的第一行数据
        lineList.remove(0);

        //将数据封装到list<Map>中
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (List<Object> objects : lineList) {
            if (null != objects) {
                Map<String, Object> hashMap = new HashMap<>();
                for (int j = 0; j < columNames.length; j++) {
                    Object property = objects.get(j);
                    hashMap.put(columNames[j], property);
                }
                dataList.add(hashMap);
            } else {
                break;
            }
        }
        return dataList;
    }

    private static void isCorrectParam(MultipartFile file, String fileName) {
        // 上传文件为空
        if (StringUtils.isEmpty(fileName)) {
            throw new OperationException("没有导入文件");
        }
        //上传文件大小为1000条数据
        if (file.getSize() > 1024 * 1024 * 10) {
            logger.error("upload | 上传失败: 文件大小超过10M，文件大小为：{}", file.getSize());
            throw new OperationException("上传失败: 文件大小不能超过10M!");
        }
        // 上传文件名格式不正确
        if (fileName.lastIndexOf(".") != -1 && !".xlsx".equals(fileName.substring(fileName.lastIndexOf(".")))) {
            throw new OperationException("文件名格式不正确, 请使用后缀名为.XLSX的文件");
        }
    }

    /**
     * 通过实现handle方法编写我们要对每行数据的操作方式
     */
    private static RowHandler createRowHandler() {
        //清空一下集合中的数据
        lineList.removeAll(lineList);
        return new RowHandler() {

            /**
             * 处理一行数据
             *
             * @param sheetIndex 当前Sheet序号
             * @param rowIndex   当前行号，从0开始计数
             * @param rowCells   行数据，每个Object表示一个单元格的值
             */
            @Override
            public void handle(int sheetIndex, long rowIndex, List<Object> rowCells) {
                //将读取到的每一行数据放入到list集合中
                JSONArray jsonObject = new JSONArray(rowCells);
                lineList.add(jsonObject.toList(Object.class));
            }
        };
    }

    /**
     * excel导入工具类
     *
     * @param file   文件
     * @param aClass aClass
     * @return 返回数据集合
     * @throws OperationException
     * @throws IOException
     */
    public static <T> List<T> leading(MultipartFile file, Class<T> aClass) throws OperationException, IOException {
        String fileName = file.getOriginalFilename();
        isCorrectParam(file, fileName);
        //读取数据
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream(), 0);
        return reader.readAll(aClass);
    }

    public static void main(String[] args) throws FileNotFoundException {
        //读取数据
        String name = "D:\\ZLZN\\IdeaProjects\\practice\\excle-demo\\src\\main\\resources\\aa.xlsx";
        List<User> userList = EasyExcel.read(name).head(User.class).sheet().doReadSync();
        System.out.println(userList);
    }
}
