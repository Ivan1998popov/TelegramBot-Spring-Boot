package com.telegram.springboot.ParsingDirectionOfSpecialties;

public class DataParsingSpecialties {

    public String id_name;
    public String name_spec;
    public String subjects;
    public String marks;
    public String structural;

    public DataParsingSpecialties(String id_name,String name_spec, String subjects, String marks, String structural) {
        this.id_name=id_name;
        this.name_spec = name_spec;
        this.subjects = subjects;
        this.marks = marks;
        this.structural = structural;
    }

    public String getId_name() {
        return id_name;
    }

    public void setId_name(String id_name) {
        this.id_name = id_name;
    }

    public String getName_spec() {
        return name_spec;
    }

    public void setName_spec(String name_spec) {
        this.name_spec = name_spec;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getStructural() {
        return structural;
    }

    public void setStructural(String structural) {
        this.structural = structural;
    }

    @Override
    public String toString() {
        return "DataParsingSpecialties{" +
                "id_name='" + id_name + '\'' +
                ", name_spec='" + name_spec + '\'' +
                ", subjects='" + subjects + '\'' +
                ", marks='" + marks + '\'' +
                ", structural='" + structural + '\'' +
                '}';
    }
}
