package pro1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import pro1.apiDataModel.SpecializationsList;

public class Main7 {
    public static void main(String[] args) {
        System.out.println(specializationDeadlines (2025));
    }

    public static String specializationDeadlines (int year){
        String json = Api.getSpecializations(year);
        SpecializationsList specializations = new Gson().fromJson(json, SpecializationsList.class);
        DateTimeFormatter dfm = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return specializations.items.stream().map(t -> t.value).map(u -> u.value).distinct().sorted(Comparator.comparing(v -> nuly(v))).collect(Collectors.joining(","));
    }

    public static LocalDate nuly(String date){
        String data[] = date.split("\\.");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if(data[0].length()==1)
            data[0] = "0" + data[0];
        if(data[1].length()==1)
            data[1] = "0" + data[1];
        return LocalDate.parse(data[0]+"."+data[1]+"."+data[2],dtf);
    }
}
