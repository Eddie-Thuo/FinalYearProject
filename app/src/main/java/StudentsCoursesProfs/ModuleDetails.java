package StudentsCoursesProfs;

import java.util.Date;
import java.util.UUID;

/**
 * Created by eddiethuo on 11/04/2017.
 */

public class ModuleDetails {
    private UUID moduleId;
    private UUID departmentId;
    private String departmentName;
    private String moduleName;
    private int credits;
    private Date januaryExamDate;
    private Date juneExamDate;
    private String venue;

    public UUID getModuleId() {
        return moduleId;
    }

    public void setModuleId(UUID moduleId) {
        this.moduleId = moduleId;
    }

    public UUID getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(UUID departmentId) {
        this.departmentId = departmentId;
    }

    public String getDeparmentName() {
        return departmentName;
    }

    public void setDeparmentName(String deparmentName) {
        this.departmentName = deparmentName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Date getJanuaryExamDate() {
        return januaryExamDate;
    }

    public void setJanuaryExamDate(Date januaryExamDate) {
        this.januaryExamDate = januaryExamDate;
    }

    public Date getJuneExamDate() {
        return juneExamDate;
    }

    public void setJuneExamDate(Date juneExamDate) {
        this.juneExamDate = juneExamDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }
}
