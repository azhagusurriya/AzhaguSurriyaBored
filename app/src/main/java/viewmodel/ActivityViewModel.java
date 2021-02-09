package viewmodel;

import androidx.lifecycle.ViewModel;

import model.Bored;
import repository.ActivityRepository;

public class ActivityViewModel extends ViewModel {

    private static final ActivityViewModel ourInstance = new ActivityViewModel();
    private final ActivityRepository activityRepository = new ActivityRepository();

    public static ActivityViewModel getInstance(){
        return ourInstance;
    }

    private ActivityViewModel(){
    }

    public void addActivity(Bored activity){
        this.activityRepository.addActivity(activity);
    }
    public ActivityRepository getActivityRepository(){
        return activityRepository;
    }

}
