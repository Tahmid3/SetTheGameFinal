package Code;

public class Clock {

    int hour1 = 0;
    int hour2 = 0;
    int minute1 = 0;
    int minute2 = 0;
    int second1 = 0;
    int second2 = 0;

    @Override
    public String toString() {
        String s1 = String.valueOf(hour1);
        String s2 = String.valueOf(hour2);
        String s3 = String.valueOf(minute1);
        String s4 = String.valueOf(minute2);
        String s5 = String.valueOf(second1);
        String s6 = String.valueOf(second2);
        return s1 + s2 + ":" + s3 + s4 + ":" + s5 + s6;


    }

    public String start() {
            second2++;
            if (second2 == 10) {
                second2 = 0;
                second1++;

                if (second1 == 6) {
                    minute2++;
                    second1 = 0;
                    if (minute2 == 10) {
                        minute1++;
                        minute2 = 0;
                        if (minute1 == 6) {
                            hour2++;
                            minute1 = 0;
                            if (hour2 == 10) {
                                hour1++;
                                hour2 = 0;
                                if (hour1 == 10) {

                                }
                            }
                        }
                    }
                }
            }
            return this.toString();
        }
}
