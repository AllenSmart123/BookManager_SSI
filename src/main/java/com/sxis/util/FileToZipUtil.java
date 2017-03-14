/*
 * NOTE: This copyright does *not* cover user programs that use HQ
 * program services by normal system calls through the application
 * program interfaces provided as part of the Hyperic Plug-in Development
 * Kit or the Hyperic Client Development Kit - this is merely considered
 * normal use of the program, and does *not* fall under the heading of
 * "derived work".
 * 
 * Copyright (C) [2004-2008], Hyperic, Inc.
 * This file is part of HQ.
 * 
 * HQ is free software; you can redistribute it and/or modify
 * it under the terms version 2 of the GNU General Public License as
 * published by the Free Software Foundation. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 */

package com.sxis.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileToZipUtil {
	public final static Logger logger = LoggerFactory.getLogger( FileToZipUtil.class );
	public static final int BUFFER = 8192;    
	public static File zipFile; 

    private FileToZipUtil(){}  
      
    /** 
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下 
     * @param sourceFilePath :待压缩的文件路径 
     * @param zipFilePath :压缩后存放路径 
     * @param fileName :压缩后文件的名称 
     * @return 
     */  
    public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){  
        boolean flag = false;  
        File sourceFile = new File(sourceFilePath);  
        FileInputStream fis = null;  
        BufferedInputStream bis = null;  
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  
          
        if(sourceFile.exists() == false){  
            System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");  
        }else{  
            try {  
                File zipFile1 = new File(zipFilePath + "/" + fileName +".zip");  
                if(zipFile1.exists()){  
                    System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");  
                }else{  
                    File[] sourceFiles = sourceFile.listFiles();  
                    if(null == sourceFiles || sourceFiles.length<1){  
                        System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");  
                    }else{  
                        fos = new FileOutputStream(zipFile1);  
                        zos = new ZipOutputStream(new BufferedOutputStream(fos));  
                        byte[] bufs = new byte[1024*10];  
                        for(int i=0;i<sourceFiles.length;i++){  
                            //创建ZIP实体，并添加进压缩包  
                            ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());  
                            zos.putNextEntry(zipEntry);  
                            //读取待压缩的文件并写进压缩包里  
                            fis = new FileInputStream(sourceFiles[i]);  
                            bis = new BufferedInputStream(fis, 1024*10);  
                            int read = 0;  
                            while((read=bis.read(bufs, 0, 1024*10)) != -1){  
                                zos.write(bufs,0,read);  
                            }  
                        }  
                        flag = true;  
                    }  
                }  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } catch (IOException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            } finally{  
                //关闭流  
                try {  
                    if(null != bis) bis.close();  
                    if(null != zos) zos.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                    throw new RuntimeException(e);  
                }  
            }  
        }  
        return flag;  
    } 
    
    
    /** 
     * 压缩一个文件 
     * @param file 
     * @param out 
     * @param basedir 
     */  
    public static void compressFile(File file, ZipOutputStream out, String basedir) {    
        if (!file.exists()) {    
            return;    
        }    
        try {    
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));    
            ZipEntry entry = new ZipEntry(basedir + file.getName());    
            out.putNextEntry(entry);    
            int count;    
            byte data[] = new byte[BUFFER];    
            while ((count = bis.read(data, 0, BUFFER)) != -1) {    
                out.write(data, 0, count);    
            }    
            bis.close();    
        } catch (Exception e) {    
            throw new RuntimeException(e);    
        }    
    }    
    
    /** 
     * 压缩一个目录 
     * @param dir 
     * @param out 
     * @param basedir 
     */  
    public static void compressDirectory(File dir, ZipOutputStream out, String basedir) {    
        if (!dir.exists()){  
             return;    
        }  
             
        File[] files = dir.listFiles();    
        for (int i = 0; i < files.length; i++) {    
            /* 递归 */    
            compressByType(files[i], out, basedir + dir.getName() + "/");    
        }    
    }  
    
    /** 
     * 判断是目录还是文件，根据类型（文件/文件夹）执行不同的压缩方法 
     * @param file  
     * @param out 
     * @param basedir 
     */  
    private static void compressByType(File file, ZipOutputStream out, String basedir) {    
        /* 判断是目录还是文件 */    
        if (file.isDirectory()) {    
            logger.info("压缩：" + basedir + file.getName());    
            compressDirectory(file, out, basedir);    
        } else {    
            logger.info("压缩：" + basedir + file.getName());    
            compressFile(file, out, basedir);    
        }    
    } 
    
    /** 
     * 执行压缩操作 
     * @param srcPathName 被压缩的文件/文件夹 
     */  
    public static void compressExe(String pathName,String srcPathName) {    
        File file = new File(srcPathName);    
        if (!file.exists()){  
            throw new RuntimeException(srcPathName + "不存在！");    
        }  
        try {
        	File zipFile = new File(pathName);
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);    
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());    
            ZipOutputStream out = new ZipOutputStream(cos);    
            String basedir = "";    
            compressByType(file, out, basedir);    
            out.close();    
        } catch (Exception e) {   
            e.printStackTrace();  
            logger.error("执行压缩操作时发生异常:"+e);  
            throw new RuntimeException(e);    
        }    
    } 
    
    public static void main(String[] args){
    	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    	//sdf.format(new Date());
    	//System.out.println(sdf.format(new Date()));
    	//DateFormat.getDateTimeInstance().format(new Date())
    	FileToZipUtil.compressExe("E:\\SIEM_"+new SimpleDateFormat("yyyy-MM-dd#HH-mm-ss").format(new Date())+"_Backup.zip","D:\\jisupdf");
    }  
	
}
