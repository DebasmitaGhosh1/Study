package com.infowebmentsolution.wedusuccess.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infowebmentsolution.wedusuccess.Gson.AllPlanList;
import com.infowebmentsolution.wedusuccess.R;

import java.util.ArrayList;

public class AllPlanAdapter extends RecyclerView.Adapter<AllPlanAdapter.ViewHolder> {
    private ArrayList<AllPlanList> result1;
    private ClickedOnItemListener clickedOnItemListener;
    int uni_number;


    public void setData(ArrayList<AllPlanList> result) {


        this.result1 = result;

        for(int i =0; i<this.result1.size(); i=i+1){
            if(result1.get(i).getUniversitystatus().equals("0") ||result1.get(i).getBranchstatus().equals("0")
                    ||result1.get(i).getSemesterstatus().equals("0")|| result1.get(i).getSubscriptionstatus().equals("0")) {
                this.result1.remove(i);
            }
        }
        for(int i =0; i<this.result1.size(); i=i+1){
            if(result1.get(i).getUniversitystatus().equals("0") ||result1.get(i).getBranchstatus().equals("0")
                    ||result1.get(i).getSemesterstatus().equals("0")|| result1.get(i).getSubscriptionstatus().equals("0")) {
                this.result1.remove(i);
            }
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_all_plan, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.uni_name.setText(result1.get(i).getUniversityname());
        viewHolder.branch_name.setText(result1.get(i).getBranchname());
        viewHolder.sem_name.setText(result1.get(i).getSemestername());
        viewHolder.subscription_amount.setText(result1.get(i).getSubscriptionamount());
        viewHolder.subscription_validation.setText(result1.get(i).getSubscriptionduration());

        AllPlanList ul = result1.get(i);
        viewHolder.pay_button.setOnClickListener(v -> clickedOnItemListener.ClickedItem(ul));
    }

    @Override
    public int getItemCount() {
        return result1.size();
    }

    public interface ClickedOnItemListener {
        void ClickedItem(AllPlanList allPlanList);
    }

    public AllPlanAdapter(ClickedOnItemListener clickedOnItemListener){
        this.clickedOnItemListener = clickedOnItemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView uni_name;
        private  TextView branch_name;
        private  TextView sem_name;
        private TextView subscription_amount;
        private TextView subscription_validation;
        private TextView subjectname;
        private TextView category_name;
        private TextView subCategoryname;
        private Button pay_button;

        public ViewHolder(View view) {
            super(view);

            uni_name = (TextView) view.findViewById(R.id.subscription_uni_name);
            branch_name =(TextView) view.findViewById(R.id.subscription_branch_name);
            sem_name =(TextView) view.findViewById(R.id.subscription_sem_name);
            subscription_amount =(TextView) view.findViewById(R.id.subscription_amount);
            subscription_validation =(TextView) view.findViewById(R.id.subscription_validation);
            pay_button =(Button) view.findViewById(R.id.subscription_payment_button);

        }
    }
}
