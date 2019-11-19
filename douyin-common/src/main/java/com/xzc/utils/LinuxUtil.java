package com.xzc.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaozhichao
 * @Date 2019/11/18
 * @Time 22:34
 */
public class LinuxUtil {

    public static void main(String[] args) {
        String cmd = "sort -t,  -k2n,2  -k1,1   aa";
        LinuxUtil.exec(cmd);

        List<String> commands = new ArrayList<>();
        commands.add("cd /home/whj/tmp/hello");
        commands.add("sort -t,  -k2n,2  -k1,1   aa");
        commands.add("sort -t,  -k2n,2  -k1,1   aa > vv");
        LinuxUtil.execFlow(commands);
        for (String a:
             commands) {
            System.out.println(a+" ");
        }
    }


    public static Object execFlow(List<String> commands) {
        try {
            String cmds = "";
            for (String cmd : commands) {
                cmds += cmd + ";";
            }
            String[] cmdA = {"/bin/sh", "-c", cmds};
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line).append("\n");
            }
            if (br != null) { //关闭流
                br.close();
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static Object exec(String cmd) {
        try {
            String[] cmdA = {"/bin/sh", "-c", cmd};
            Process process = Runtime.getRuntime().exec(cmdA);
            LineNumberReader br = new LineNumberReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}


