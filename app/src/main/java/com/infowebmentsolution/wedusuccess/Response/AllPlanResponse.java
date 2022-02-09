package com.infowebmentsolution.wedusuccess.Response;

import com.infowebmentsolution.wedusuccess.Gson.AllPlanList;

public class AllPlanResponse {

    private int status;
    private String message;
    private AllPlanList[] data;
    public AllPlanList[] getData(){
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
