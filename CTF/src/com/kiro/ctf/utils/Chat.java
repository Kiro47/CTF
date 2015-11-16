package com.kiro.ctf.utils;

import java.util.regex.Pattern;

public class Chat {


	private static final Pattern COMPILE = Pattern.compile("(&|\247)([0-9a-f|k|l|m|n|])");

	public Chat()
	{
	}

	public static void main(String args[])
	{
		System.out.println(45);
		System.out.println(fill("-"));
	}

	public static int charWidth(char s)
	{
		if (s == ' ')
		{
			return 4;
		}
		else
		{
			return FONT_SIZES[s + 32];
		}
	}

	public static int stringWidth(String s)
	{
		if (s == null)
		{
			return 0;
		}
		s = COMPILE.matcher(s).replaceAll("");
		int size = 0;
		for (int index = 0; index < s.length(); index++)
		{
			char ch = s.charAt(index);
			size += charWidth(ch);
		}

		return size;
	}

	public static String fill(String filler)
	{
		int size = stringWidth(filler);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i * size < MAX_WIDTH; i++)
		{
			builder.append(filler);
		}

		return builder.toString();
	}

	public static String center(String s)
	{
		int strSize = stringWidth(s);
		int fillerSize = stringWidth(" ");
		StringBuilder string = new StringBuilder();
		int toFill = MAX_WIDTH / 2 - strSize / 2;
		for (int i = 0; i * fillerSize < toFill; i++)
		{
			string.append(' ');
		}

		string.append(s);
		return string.toString();
	}

	public static String format(String in)
	{
		if (in == null)
		{
			return "";
		}
		else
		{
			return in.replaceAll("&([0-9a-f|k|l|m|n])", "\247$1");
		}
	}


	private static int[] FONT_SIZES = {
			                                  6, 6, 6, 6, 6, 6, 4, 6, 6, 6,
			                                  6, 6, 6, 6, 6, 4, 4, 6, 7, 6,
			                                  6, 6, 6, 6, 6, 1, 1, 1, 1, 1,
			                                  1, 1, 1, 2, 5, 6, 6, 6, 6, 3,
			                                  5, 5, 5, 6, 2, 6, 2, 6, 6, 6,
			                                  6, 6, 6, 6, 6, 6, 6, 6, 2, 2,
			                                  5, 6, 5, 6, 7, 6, 6, 6, 6, 6,
			                                  6, 6, 6, 4, 6, 6, 6, 6, 6, 6,
			                                  6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
			                                  6, 4, 6, 4, 6, 6, 3, 6, 6, 6,
			                                  6, 6, 5, 6, 6, 2, 6, 5, 3, 6,
			                                  6, 6, 6, 6, 6, 6, 4, 6, 6, 6,
			                                  6, 6, 6, 5, 2, 5, 7, 6, 6, 6,
			                                  6, 6, 6, 6, 6, 6, 6, 6, 6, 4,
			                                  6, 3, 6, 6, 6, 6, 6, 6, 6, 6,
			                                  6, 6, 6, 6, 6, 6, 6, 6, 4, 6,
			                                  6, 3, 6, 6, 6, 6, 6, 6, 6, 7,
			                                  6, 6, 6, 2, 6, 6, 8, 9, 9, 6,
			                                  6, 6, 8, 8, 6, 8, 8, 8, 8, 8,
			                                  6, 6, 9, 9, 9, 9, 9, 9, 9, 9,
			                                  9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
			                                  9, 9, 9, 9, 9, 9, 9, 6, 9, 9,
			                                  9, 5, 9, 9, 8, 7, 7, 8, 7, 8,
			                                  8, 8, 7, 8, 8, 7, 9, 9, 6, 7,
			                                  7, 7, 7, 7, 9, 6, 7, 8, 7, 6,
			                                  6, 9, 7, 6, 7, 1
	};
	public static int MAX_WIDTH = 318;

}
