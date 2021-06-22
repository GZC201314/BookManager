package org.bsm.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 判断 数组/集合 为空的工具类
 *
 * @author
 */
public class DateUtil {
  /**
   * 获取未来 第 past 天的日期
   *
   * @param past
   * @return
   */
  public static Date getPastDate(int past) {
    Date now = new Date();
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(now);
    calendar.add(Calendar.DAY_OF_MONTH, -7);
    return calendar.getTime();
  }
}
