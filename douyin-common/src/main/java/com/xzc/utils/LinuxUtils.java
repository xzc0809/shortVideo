package com.xzc.utils;

import com.google.common.base.Joiner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaozhichao
 * @Date 2019/11/18
 * @Time 22:42
 */
public class LinuxUtils {

    public static String execute(String[] cmd) {
        String returnString = "";
        Process pro = null;
        Runtime runTime = Runtime.getRuntime();
        if (runTime == null) {
            System.err.println("Create runtime false!");
        }
        try {
            System.out.println("开始转换");
            pro = runTime.exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            PrintWriter output = new PrintWriter(new OutputStreamWriter(pro.getOutputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println("line: " + line);
                returnString = returnString + line + "\n";
            }
            System.out.println("返回值:" + returnString);
            input.close();
            output.close();
            pro.destroy();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return returnString;
    }



    /**
     * 合成视频
     * @param videoInputPath
     * @param mp3InputPath
     * @param seconds
     * @param videoOutputPath
     * @throws Exception
     */
    public void convertor(String videoInputPath, String mp3InputPath,
                          double seconds, String videoOutputPath) throws Exception {
//		ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4

        String[] command={"ffmpeg","-i", videoInputPath,
                "-i",mp3InputPath,
                "-t",String.valueOf(seconds),
                "-y",videoOutputPath};
        execute(command);

//        List<String> command = new ArrayList<>();
//        String cmd="ffmpeg -i"+
//                " "+ videoInputPath+
//                " "+"-i" +
//                " "+mp3InputPath+
//                " "+"-t"+
//                " "+String.valueOf(seconds)+
//                " "+"-y"+
//                " "+videoOutputPath;
//        execute(cmd);
//        command.add("ffmpeg");
//
//        command.add("-i");
//        command.add(videoInputPath);
//
//        command.add("-i");
//        command.add(mp3InputPath);
//
//        command.add("-t");
//        command.add(String.valueOf(seconds));
//
//        command.add("-y");
//        command.add(videoOutputPath);

    }
    /**
     * @Author xiaozhichao
     * @Description //TODO 消除视频背景音
     * @Date 18:06 2019/11/7
     * @Param * @param null
     * @return
     **/

    public void removeVoice(String videoInputPath,String videoOutputPath) throws Exception {
//		ffmpeg.exe -i video.mp4 -vcodec copy -an video2.mp4
        String[] command={"ffmpeg","-i",videoInputPath,
                "-vcodec","copy","-an",videoOutputPath};

//        List<String> command = new ArrayList<>();
//        command.add("ffmpeg");
//
//        command.add("-i");
//        command.add(videoInputPath);
//
//        command.add("-vcodec");
//        command.add("copy");
//        command.add("-an");
//
//        command.add(videoOutputPath);

       execute(command);

    }

    /**
     * 生成封面
     * @param videoInputPath
     * @param coverOutputPath
     * @throws Exception
     */
    public void getCover(String videoInputPath, String coverOutputPath) throws Exception {
//		ffmpeg.exe -ss 00:00:01 -i spring.mp4 -vframes 1 bb.jpg
        String[] command={"ffmpeg","-ss","00:00:01","-y","-i",videoInputPath,"-vframes","1",coverOutputPath};
        execute(command);
        //        List<String> command = new java.util.ArrayList<String>();
//        command.add("ffmpeg");
//
//        // 指定截取第1秒
//        command.add("-ss");
//        command.add("00:00:01");
//
//        command.add("-y");
//        command.add("-i");
//        command.add(videoInputPath);
//
//        command.add("-vframes");
//        command.add("1");
//
//        command.add(coverOutputPath);




    }

    public static void main(String[] args) throws Exception {

        String[] command={"cd",".."};
        System.out.println(execute(command));


    }

//    /***
//     * 第一种方法，仅仅执行命令无须关注返回结果
//     * @throws Exception
//     */
//    public static void exeCmd(List<String> cmd) throws Exception{
//
//        Runtime r = Runtime.getRuntime();
//        //执行linux命令，不关心返回结果，此处，可以执行一个shell脚本，或者python脚本
//        String a = Joiner.on(" ").join(cmd);//list转字符串
//        Process p = r.exec(a);
//        int b=p.waitFor();
//        System.out.println(b);
//    }

}
