package com.xzc.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {

	private String ffmpegEXE;
	
	public MergeVideoMp3(String ffmpegEXE) {
		super();
		this.ffmpegEXE = ffmpegEXE;
	}
	
	public void convertor(String videoInputPath, String mp3InputPath,
			double seconds, String videoOutputPath) throws Exception {
//		ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);
		
		command.add("-i");
		command.add(videoInputPath);
		
		command.add("-i");
		command.add(mp3InputPath);
		
		command.add("-t");
		command.add(String.valueOf(seconds));
		
		command.add("-y");
		command.add(videoOutputPath);
		
//		for (String c : command) {
//			System.out.print(c + " ");
//		}

		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		
		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		
		String line = "";
		while ( (line = br.readLine()) != null ) {
		}
		
		if (br != null) {
			br.close();
		}
		if (inputStreamReader != null) {
			inputStreamReader.close();
		}
		if (errorStream != null) {
			errorStream.close();
		}
		
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
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);

		command.add("-i");
		command.add(videoInputPath);

		command.add("-vcodec");
		command.add("copy");
		command.add("-an");

		command.add(videoOutputPath);

		for (String c : command) {
			System.out.print(c + " ");
		}

		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();

		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);

		String line = "";
		while ( (line = br.readLine()) != null ) {
		}

		if (br != null) {
			br.close();
		}
		if (inputStreamReader != null) {
			inputStreamReader.close();
		}
		if (errorStream != null) {
			errorStream.close();
		}

	}
//    public void delFile(String path,String fileName) throws Exception {
////		del video2.mp4
//        List<String> command = new ArrayList<>();
//        command.add(path);
//
//        command.add("del");
//        command.add(fileName);
//
//
//        ProcessBuilder builder = new ProcessBuilder(command);
//        Process process = builder.start();
//
//        InputStream errorStream = process.getErrorStream();
//        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
//        BufferedReader br = new BufferedReader(inputStreamReader);
//
//        String line = "";
//        while ( (line = br.readLine()) != null ) {
//        }
//
//        if (br != null) {
//            br.close();
//        }
//        if (inputStreamReader != null) {
//            inputStreamReader.close();
//        }
//        if (errorStream != null) {
//            errorStream.close();
//        }
//
//    }





    public static void main(String[] args) {
		MergeVideoMp3 ffmpeg = new MergeVideoMp3("H:\\tools\\ffmpeg\\bin\\ffmpeg.exe");
		try {
//			ffmpeg.convertor("H:\\gitReponsitory\\douyin\\douyin_userFiles\\1001\\video\\newVideo.mp4", "H:\\gitReponsitory\\douyin\\douyin_userFiles\\bgm\\Creep.mp3", 10, "H:\\gitReponsitory\\douyin\\douyin_userFiles\\1001\\video\\newVideo2.mp4");
//			ffmpeg.removeVoice("H:\\gitReponsitory\\douyin\\douyin_userFiles\\1001\\video\\newVideo.mp4","H:\\gitReponsitory\\douyin\\douyin_userFiles\\1001\\video\\newVideo2.mp4");
//		    ffmpeg.delFile("H:\\gitReponsitory\\douyin\\douyin_userFiles\\1001\\video","50bf0ca4-7a25-4540-a761-18ba10a4ea6e2.mp4");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
