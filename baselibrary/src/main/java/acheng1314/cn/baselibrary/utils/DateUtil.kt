package acheng1314.cn.baselibrary.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private val dateFormater = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        }
    }

    private val dateFormater2 = object : ThreadLocal<SimpleDateFormat>() {
        override fun initialValue(): SimpleDateFormat {
            return SimpleDateFormat("yyyy-MM-dd")
        }
    }

    fun int2Date(timestampString: String): String {
        val timestamp = java.lang.Long.parseLong(timestampString) * 1000
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(timestamp))
    }

    fun int2Time(timestampString: String): String {
        val timestamp = java.lang.Long.parseLong(timestampString) * 1000
        return SimpleDateFormat("HH:mm:ss").format(Date(timestamp))
    }


    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    fun toDate(sdate: String): Date? {
        return toDate(sdate, dateFormater.get())
    }

    fun toDate(sdate: String, dateFormater: SimpleDateFormat): Date? {
        try {
            return dateFormater.parse(sdate)
        } catch (e: ParseException) {
            return null
        }

    }

    /**
     * 判断用户的设备时区是否为东八区（中国） 2014年7月31日
     *
     * @return
     */
    fun isInEasternEightZones(): Boolean {
        var defaultVaule = true
        defaultVaule = TimeZone.getDefault() === TimeZone.getTimeZone("GMT+08")
        return defaultVaule
    }

    /**
     * 根据不同时区，转换时间 2014年7月31日
     */
    fun transformTime(date: Date?, oldZone: TimeZone,
                      newZone: TimeZone): Date? {
        var finalDate: Date? = null
        if (date != null) {
            val timeOffset = oldZone.getOffset(date.time) - newZone.getOffset(date.time)
            finalDate = Date(date.time - timeOffset)
        }
        return finalDate
    }


    /**
     * 以友好的方式显示时间
     *
     * @param sdate
     * @return
     */
    fun friendlyTime(sdate: String): String {
        var time: Date? = null

        if (isInEasternEightZones()) {
            time = toDate(sdate)
        } else {
            time = transformTime(toDate(sdate), TimeZone.getTimeZone("GMT+08"),
                    TimeZone.getDefault())
        }

        if (time == null) {
            return "Unknown"
        }
        var ftime = ""
        val cal = Calendar.getInstance()

        // 判断是否是同一天
        val curDate = dateFormater2.get().format(cal.time)
        val paramDate = dateFormater2.get().format(time)
        if (curDate == paramDate) {
            val hour = ((cal.timeInMillis - time.time) / 3600000).toInt()
            if (hour == 0)
                ftime = Math.max(
                        (cal.timeInMillis - time.time) / 60000, 1).toString() + "分钟前"
            else
                ftime = hour.toString() + "小时前"
            return ftime
        }

        val lt = time.time / 86400000
        val ct = cal.timeInMillis / 86400000
        val days = (ct - lt).toInt()
        if (days == 0) {
            val hour = ((cal.timeInMillis - time.time) / 3600000).toInt()
            if (hour == 0)
                ftime = Math.max(
                        (cal.timeInMillis - time.time) / 60000, 1).toString() + "分钟前"
            else
                ftime = hour.toString() + "小时前"
        } else if (days == 1) {
            ftime = "昨天"
        } else if (days == 2) {
            ftime = "前天 "
        } else if (days in 3..30) {
            ftime = days.toString() + "天前"
        } else if (days >= 31 && days <= 2 * 31) {
            ftime = "一个月前"
        } else if (days > 2 * 31 && days <= 3 * 31) {
            ftime = "2个月前"
        } else if (days > 3 * 31 && days <= 4 * 31) {
            ftime = "3个月前"
        } else {
            ftime = dateFormater2.get().format(time)
        }
        return ftime
    }

}