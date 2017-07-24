package activityscheduling;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

class Activity implements Comparable<Activity>{
    int start, end;
    int profit;
    
    Activity(){}
    Activity(int a, int b, int c){
        start =  a;
        end = b;
        profit = c;
    }
    
    public int compareTo(Activity var){
        if(end < var.end)
            return -1;
        else if(end > var.end)
            return 1;
        else
            return 0;
    }
    
    public String toString(){
        return "(" + start + "," + end + ")";
    }
}

public class ActivityScheduling {

    static public int binarySearch(Activity activities[], int index)
    {
        int left = 0;
        int right = index - 1;
 
        while (left <= right)
        {
            int middle = (left + right) / 2;
            if (activities[middle].end <= activities[index].start)
            {
                if (activities[middle + 1].end <= activities[index].start)
                    left = middle + 1;
                else
                    return middle;
            }
            else
                right = middle - 1;
        }
 
        return -1;
    }
    
    static public ArrayList<Activity> activitySchedule(Activity activities[], int n)
    {
        ArrayList<ArrayList<Activity>> selectedActivities = new ArrayList<ArrayList<Activity>>();
        selectedActivities.add(new ArrayList<Activity>());
        selectedActivities.get(0).add(activities[0]);

        int activitiesProfits[] = new int[n];
        activitiesProfits[0] = activities[0].profit;
 
        for (int i=1; i<n; i++)
        {
            ArrayList<ArrayList<Activity>> foundActivities = new ArrayList<ArrayList<Activity>>();
            int foundProfit = activities[i].profit;

            int l = binarySearch(activities, i);
            if (l != -1){
                foundActivities.add(selectedActivities.get(l));
                foundActivities.get(0).add(activities[i]);
                foundProfit += activitiesProfits[l];
            }
            else
            {
                foundActivities.add(new ArrayList<Activity>());
                foundActivities.get(0).add(activities[i]);
            }
            
            activitiesProfits[i] = Math.max(foundProfit, activitiesProfits[i-1]);
            
            if(activitiesProfits[i] == foundProfit && foundActivities.size() > 0)
                selectedActivities.add(foundActivities.get(0));
            else
                selectedActivities.add(selectedActivities.get(i-1));
        }
 
        return selectedActivities.get(n-1);
    }

    public static void main(String[] args) {
        File file = new File("activities.txt");
        Activity[] activities;
        ArrayList<Activity> selectedActivities;
        int n;
        
        try{
            
        Scanner activityScanner = new Scanner(file);
        
        n = activityScanner.nextInt();
        activities = new Activity[n];
        
        for(int i=0; i<n; i++){
            activities[i] = new Activity(activityScanner.nextInt(),activityScanner.nextInt(),activityScanner.nextInt());
        }
        
        Arrays.sort(activities);
        
        selectedActivities = activitySchedule(activities, n);
        
        int maxProfit = 0;
        
        for(int i=0; i<selectedActivities.size(); i++){
            maxProfit += selectedActivities.get(i).profit;
        }
        
        System.out.println(maxProfit);
        
        System.out.println(selectedActivities);
        
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }
    
}
