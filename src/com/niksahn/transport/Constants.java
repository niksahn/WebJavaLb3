package com.niksahn.transport;


import java.util.regex.Pattern;

class Constants {
    private static final String number_regex = "^ *[A-Za-z]([0-9]){3}[A-Za-z]{2}([0-9]){2} *$";
    public static Pattern number_pattern = Pattern.compile(number_regex);
    private static final String positive_int_regex = "^ *[1-9][0-9]{0,8} *$";
    public static Pattern positive_int_pattern = Pattern.compile(positive_int_regex);
    private static final String string_regex = "^ *[A-Za-zА-Яа-я]* *$";
    public static Pattern string_pattern = Pattern.compile(string_regex);
    private static final String hand_regex = "^ *([пП]раворульный)|([лЛ]еворульный) *$";
    public static Pattern hand_pattern = Pattern.compile(hand_regex);
    private static final String transmission_regex = "^ *([аА]вто)|([мМ]еханика) *$";
    public static Pattern transmission_pattern = Pattern.compile(transmission_regex);

    private static final String drive_regex = "^ *([Пп]олный)|([пП]ередний)|([зЗ]адний) *$";
    public static Pattern drive_pattern = Pattern.compile(drive_regex);

}
