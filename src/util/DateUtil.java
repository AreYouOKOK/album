package util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
  public static String formatDate(Date date){
	  final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  return format.format(date);
  }
}
