package com.hyj.intermediate.newfeaturejdk8;

import javafx.concurrent.Task;

import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.Base64;
import java.util.Optional;

/**
 * @version 1.0
 * @Authord yujie huang  email: 190158792@qq.com
 * @Description
 * @Date Create by in 15:01 2019/2/20
 */
public class newjar {
    public static void main(String[] args) throws Exception{
        //4. Java官方库的新特性
        //Optional
//        optionalTest(null);
//        optionalTest("hyj");
        //stream
        //Date/Time API(JSR 310)
        //datetimeAPI();
        //Base64
        base64Test();
    }

    static void datetimeAPI(){
        //Clock类使用时区来返回当前的纳秒时间和日期
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
        //关注下LocalDate和LocalTime类。LocalDate仅仅包含ISO-8601日历系统中的日期部分；
        // LocalTime则仅仅包含该日历系统中的时间部分。这两个类的对象都可以使用Clock对象构建得到。
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.now(clock);
        System.out.println(localDate);
        System.out.println(localDate1);
        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.now(clock);
        System.out.println(localTime);
        System.out.println(localTime1);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.now(clock);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);

        // ZonedDateTime  //获取特定时区
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now( clock );
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now( ZoneId.of( "America/Los_Angeles" ) );

        System.out.println( zonedDatetime );
        System.out.println( zonedDatetimeFromClock );
        System.out.println( zonedDatetimeFromZone );
        //Duration类，它持有的时间精确到秒和纳秒。这使得我们可以很容易得计算两个日期之间的不同
        final LocalDateTime from = LocalDateTime.of( 2014, Month.APRIL, 16, 0, 0, 0 );
        final LocalDateTime to = LocalDateTime.of( 2015, Month.APRIL, 16, 23, 59, 59 );
        Duration duration = Duration.between(from,to);
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());

        //Base64

    }

    static void optionalTest(String param){
        Optional<String> optionalS = Optional.ofNullable(param);
        System.out.println(optionalS.isPresent());
        System.out.println(optionalS.orElseGet(()->"[none]"));
        System.out.println(optionalS.orElse("hey hyj!"));
        System.out.println(optionalS.map(s -> "hey "+s+"!").orElse("hey stranger!"));
    }

    static void base64Test()throws Exception{
        String s = "Base64 began in java8";
        String encode = Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);
        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println(new String(decode,StandardCharsets.UTF_8));
    }
}
