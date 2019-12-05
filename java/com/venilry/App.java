package com.venilry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
/*
 * java.sql.Date对应mysql的date，只保留日期，格式为xxxx-xx-xx
 * java.sql.Time对应mysql的time，只保留时间，格式为xx:xx:xx
 * java.sql.Timestamp对应mysql的timestamp，同时保留日期和时间，格式为xxxx-xx-xx xx:xx:xx
 * 
 * 但是创建上述Date，Time，Timestamp的对象时，都需要一个long参数。
 * 该long参数可以通过先创建java.util.Date的对象后，通过.getTime()方法来获取。
 * 
 * 目前本人在java中没有找到对应mysql的datetime的类。
 * 
 * 
 */
