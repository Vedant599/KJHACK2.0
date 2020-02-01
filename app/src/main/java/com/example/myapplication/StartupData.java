package com.example.myapplication;

public class StartupData {
    String CompanyName,FounderProfile,Launchdate;
    String NetWorth,NumberOfPresentInvestors;
    String NoOfEmployees,ProjectType;
    StartupData(){}

    public String getCompanyName() {
        return CompanyName;
    }

    public String getNoOfEmployees() { return NoOfEmployees; }

    public String getNumberOfPresentInvestors() {
        return NumberOfPresentInvestors;
    }

    public String getLaunchdate() {
        return Launchdate;
    }

    public void setLaunchdate(String launchdate) {
        Launchdate = launchdate;
    }

    public void setNumberOfPresentInvestors(String numberOfPresentInvestors) {
        NumberOfPresentInvestors = numberOfPresentInvestors;
    }

    public String getFounderProfile() {
        return FounderProfile;
    }

    public void setFounderProfile(String founderProfile) {
        FounderProfile = founderProfile;
    }

    public void setNoOfEmployees(String noOfEmployees) {
        NoOfEmployees = noOfEmployees;
    }

    public String getProjectType() {
        return ProjectType;
    }

    public void setProjectType(String projectType) {
        ProjectType = projectType;
    }

    public String getNetWorth() {
        return NetWorth;
    }

    public void setNetWorth(String netWorth) {
        NetWorth = netWorth;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }
}
