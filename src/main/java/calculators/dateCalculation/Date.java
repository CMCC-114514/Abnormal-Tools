package calculators.dateCalculation;

/**
 * 自定义的简单日期类，用于替代Java标准库中的日期类。
 * 仅包含年、月、日三个整数字段，提供基本的get/set和显示方法。
 *
 * @author CMCC-114514
 */
public class Date {

    /** 年份 */
    int year;
    /** 月份（1-12） */
    int month;
    /** 日期（1-31） */
    int day;

    /**
     * 默认构造方法，将年、月、日均设为0
     */
    public Date() {
        this.year = this.month = this.day = 0;
    }

    /**
     * 带参数的构造方法
     * @param year  年份
     * @param month 月份
     * @param day   日期
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    /**
     * 以“xxxx年xx月xx日”格式打印日期
     */
    public void showDate() {
        System.out.println(year + "年" + month + "月" + day + "日");
    }

    /**
     * 以“x年x个月x天”格式打印日期（常用于表示时间段）
     */
    public void showNumOfYears() {
        System.out.print(year + "年" + month + "个月" + day + "天");
    }
}