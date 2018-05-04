package com.telegram.springboot.ParsingDirectionOfSpecialties;

public class DataParsingSpecialties {

    public String name_spec;
    public String subject_one;
    public String subject_two;
    public String subject_three;
    public String subject_one_bal;
    public String subject_two_bal;
    public String subject_three_bal;

    public DataParsingSpecialties(String name_spec, String subject_one, String subject_two, String subject_three,
                                  String subject_one_bal, String subject_two_bal, String subject_three_bal) {
        this.name_spec = name_spec;
        this.subject_one = subject_one;
        this.subject_two = subject_two;
        this.subject_three = subject_three;
        this.subject_one_bal = subject_one_bal;
        this.subject_two_bal = subject_two_bal;
        this.subject_three_bal = subject_three_bal;
    }


    public String getName_spec() {
        return name_spec;
    }

    public void setName_spec(String name_spec) {
        this.name_spec = name_spec;
    }

    public String getSubject_one() {
        return subject_one;
    }

    public void setSubject_one(String subject_one) {
        this.subject_one = subject_one;
    }

    public String getSubject_two() {
        return subject_two;
    }

    public void setSubject_two(String subject_two) {
        this.subject_two = subject_two;
    }

    public String getSubject_three() {
        return subject_three;
    }

    public void setSubject_three(String subject_three) {
        this.subject_three = subject_three;
    }

    public String getSubject_one_bal() {
        return subject_one_bal;
    }

    public void setSubject_one_bal(String subject_one_bal) {
        this.subject_one_bal = subject_one_bal;
    }

    public String getSubject_two_bal() {
        return subject_two_bal;
    }

    public void setSubject_two_bal(String subject_two_bal) {
        this.subject_two_bal = subject_two_bal;
    }

    public String getSubject_three_bal() {
        return subject_three_bal;
    }

    public void setSubject_three_bal(String subject_three_bal) {
        this.subject_three_bal = subject_three_bal;
    }

    @Override
    public String toString() {
        return "DataParsingSpecialties{" +
                "name_spec='" + name_spec + '\'' +
                ", subject_one='" + subject_one + '\'' +
                ", subject_two='" + subject_two + '\'' +
                ", subject_three='" + subject_three + '\'' +
                ", subject_one_bal='" + subject_one_bal + '\'' +
                ", subject_two_bal='" + subject_two_bal + '\'' +
                ", subject_three_bal='" + subject_three_bal + '\'' +
                '}';
    }
}
