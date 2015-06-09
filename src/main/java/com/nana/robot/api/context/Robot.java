package com.nana.robot.api.context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import com.nana.robot.chatterbean.util.Translate;

public class Robot {

	private ChartManager chartManager = null;

	public Robot() {
		chartManager = ChartManager.getInstance();
	}

	public String input(String input) {

		String output = Translate.translateString(input);
		// System.out.println("您的输入是：" + input);
		return chartManager.response(output);
	}

	public static void main(String[] args) throws IOException {
		Robot demo = new Robot();
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(System.in));
		BufferedReader br = bufferedReader();
		String input;
		while ((input = br.readLine()) != null) {
			System.out.println("you say>" + input);
			System.out.println("Alice>" + demo.input(input));
		}
	}

	private static BufferedReader bufferedReader() {
		String inputPath = "./myinput.txt";
		File file = new File(inputPath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;

	}
}
