package com.shinhan.project;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

public class WeeklyAchievementChecker {

    // 주간 범위를 담는 클래스
	@AllArgsConstructor
    static class WeekRange {
        int       weekNo;    // 몇 주차
        LocalDate weekStart; // 주 시작일
        LocalDate weekEnd;   // 주 종료일
        boolean isAchieve; //성취여부 
 

        @Override
        public String toString() {
            return String.format("%d주차: %s ~ %s", weekNo, weekStart, weekEnd);
        }
    }
 
    public static List<WeekRange> splitIntoWeeks(LocalDate joinDate, LocalDate endDate) {
        List<WeekRange> weeks = new ArrayList<>();

        // 가입일이 속한 주의 월요일
        LocalDate weekStart = joinDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        int weekNo = 1;

        while (!weekStart.isAfter(endDate)) {
            // 무조건 일요일
            LocalDate weekEnd = weekStart.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            weeks.add(new WeekRange(weekNo, weekStart, weekEnd, false));
            weekStart = weekStart.plusWeeks(1);
            weekNo++;
            //SQL문장으로 DB가서 성취 여부를 가져온후 isAchieve에 boolean으로 setting한다. 
        }

        return weeks;
    }

    public static void main(String[] args) {
        LocalDate joinDate = LocalDate.of(2026, 6, 3);   // 가입일
        LocalDate endDate  = LocalDate.of(2026, 6, 30);  // 종료일

        System.out.println("가입일: " + joinDate);
        System.out.println("종료일: " + endDate);
        System.out.println("=".repeat(40));

        List<WeekRange> weeks = splitIntoWeeks(joinDate, endDate);
        for (WeekRange week : weeks) {
            System.out.println(week);
        }
    }
}