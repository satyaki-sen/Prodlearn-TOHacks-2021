package com.satyaki.courierdemo;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import models.SendRequestBody;
import models.SendResponseBody;
import services.Courier;
import services.SendService;

public class CompeteFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    PieChart pieChart_Compete;
    TextView textName1,textScore1,textName2,textScore2,textName3,textScore3;
    String duration,totTasks;
    List<String> participants,part_Scores,taskTopics;
    CardView cardtask1,cardtask2,cardtask3,cardtask4;
    TextView textTask1,textTask2,textTask3,textTask4,textTask5;
    TextView textStatus1,textStatus2,textStatus3,textStatus4;
    Button btnTask1,btnTask2,btnTask3,btnTask4;
    TextInputLayout textLayoutText1,textLayoutText2,textLayoutText3,textLayoutText4;
    Integer tasksDone=1;
    SendRequestBody request,requestSubmit ;
    String[] arraytaskstatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Courier.init("dk_prod_T25Q2Y4DSZMTTSNS6YKN0H2XQ0M2");

        View view=inflater.inflate(R.layout.fragment_compete, container, false);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        participants=new ArrayList<>();

        arraytaskstatus= new String[]{"Complete", "Incomplete", "Incomplete", "Incomplete", "Incomplete"};

        pieChart_Compete=view.findViewById(R.id.piechart_Compete);
        textName1=view.findViewById(R.id.text_name1_compete);
        textScore1=view.findViewById(R.id.text_score1_compete);
        textName2=view.findViewById(R.id.text_name2_compete);
        textScore2=view.findViewById(R.id.text_score2_compete);
        textName3=view.findViewById(R.id.text_name3_compete);
        textScore3=view.findViewById(R.id.text_score3_compete);

        cardtask1=view.findViewById(R.id.card_task1);
        cardtask2=view.findViewById(R.id.card_task2);
        cardtask3=view.findViewById(R.id.card_task3);
        cardtask4=view.findViewById(R.id.card_task4);

        textTask1=view.findViewById(R.id.text_Tasks1_Compete);
        textTask2=view.findViewById(R.id.text_Tasks2_Compete);
        textTask3=view.findViewById(R.id.text_Tasks3_Compete);
        textTask4=view.findViewById(R.id.text_Tasks4_Compete);
        textTask5=view.findViewById(R.id.text_Tasks5_Compete);

        textStatus1=view.findViewById(R.id.text_1_Status_Compete);
        textStatus2=view.findViewById(R.id.text_2_Status_Compete);
        textStatus3=view.findViewById(R.id.text_3_Status_Compete);
        textStatus4=view.findViewById(R.id.text_4_Status_Compete);

        textLayoutText1=view.findViewById(R.id.textInput_1_Compete);
        textLayoutText2=view.findViewById(R.id.textInput_2_Compete);
        textLayoutText3=view.findViewById(R.id.textInput_3_Compete);
        textLayoutText4=view.findViewById(R.id.textInput_4_Compete);

        btnTask1=view.findViewById(R.id.btn_1_Compete);
        btnTask2=view.findViewById(R.id.btn_2_Compete);
        btnTask3=view.findViewById(R.id.btn_3_Compete);
        btnTask4=view.findViewById(R.id.btn_4_Compete);

        textLayoutText1.setVisibility(View.GONE);
        btnTask1.setVisibility(View.GONE);
        textLayoutText2.setVisibility(View.GONE);
        btnTask2.setVisibility(View.GONE);
        textLayoutText3.setVisibility(View.GONE);
        btnTask3.setVisibility(View.GONE);
        textLayoutText4.setVisibility(View.GONE);
        btnTask4.setVisibility(View.GONE);

        readBasicData();

        cardtask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textStatus1.setVisibility(View.GONE);
                btnTask1.setVisibility(View.VISIBLE);
                textLayoutText1.setVisibility(View.VISIBLE);
            }
        });

        cardtask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textStatus2.setVisibility(View.GONE);
                btnTask2.setVisibility(View.VISIBLE);
                textLayoutText2.setVisibility(View.VISIBLE);
            }
        });

        cardtask3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textStatus3.setVisibility(View.GONE);
                btnTask3.setVisibility(View.VISIBLE);
                textLayoutText3.setVisibility(View.VISIBLE);
            }
        });

        cardtask4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textStatus4.setVisibility(View.GONE);
                btnTask4.setVisibility(View.VISIBLE);
                textLayoutText4.setVisibility(View.VISIBLE);
            }
        });

        btnTask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textStatus1.setVisibility(View.VISIBLE);
                textStatus1.setText("Completed");
                btnTask1.setVisibility(View.GONE);
                textLayoutText1.setVisibility(View.GONE);
            }
        });


        btnTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textStatus2.setVisibility(View.VISIBLE);
                textStatus2.setText("Completed");
                Toast.makeText(getActivity(), "Answer Verified.", Toast.LENGTH_SHORT).show();
                btnTask2.setVisibility(View.GONE);
                textLayoutText2.setVisibility(View.GONE);
                arraytaskstatus[1]="Complete";
                tasksDone+=1;
                changeList();
                submitTask();

            }
        });

        btnTask3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textStatus3.setVisibility(View.VISIBLE);
                textStatus3.setText("Completed");
                Toast.makeText(getActivity(), "Answer Verified.", Toast.LENGTH_SHORT).show();
                btnTask3.setVisibility(View.GONE);
                textLayoutText3.setVisibility(View.GONE);
                arraytaskstatus[2]="Complete";
                tasksDone+=1;
                changeList();
                submitTask();
            }
        });

        return view;
    }

    public void loginNotification(){

        request = new SendRequestBody();
        request.setEvent("8243J3KSHN4VW0HFT1EFN0RZFE53");
        request.setRecipient("a5e0f6c8-d4cd-4e64-8b52-401c786dbb0e");

        Gson gson = new Gson(); // Convert Java Objects into JSON and back

        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("name","Satyaki");
            jsonObject.put("rank1",participants.get(0));
            jsonObject.put("rank2",participants.get(1));
            jsonObject.put("rank3",participants.get(2));
            jsonObject.put("score1",part_Scores.get(0));
            jsonObject.put("score2",part_Scores.get(1));
            jsonObject.put("score3",part_Scores.get(2));
        }
        catch (Exception e){
            e.getStackTrace();
        }

        JSONObject jsonNumberObj=new JSONObject();
        try{
            jsonNumberObj.put("phone_number","+919433093649");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        String jsonData=jsonObject.toString();
        String jsonNumber=jsonNumberObj.toString();

        Map profileMap = gson.fromJson(jsonNumber, Map.class);
        request.setProfile(new Gson().toJson(profileMap));

        Map dataMap = gson.fromJson(jsonData, Map.class);
        request.setData(new Gson().toJson(dataMap));

        CourierService courierService=new CourierService();
        courierService.execute("");

    }

    public void changeList(){

        Log.i("OCCur",part_Scores.get(2));
       String num=part_Scores.get(2);
       int new_num=Integer.parseInt(num);
       new_num+=10;
       part_Scores.add(0,String.valueOf(new_num));
       Log.i("Score",part_Scores.toString());
        //Toast.makeText(getActivity(), part_Scores.toString(), Toast.LENGTH_SHORT).show();
       part_Scores.remove(3);
       participants.add(0,"Satyaki");
       Log.i("PAr",participants.toString());
       participants.remove(3);

       readyList(tasksDone,3);
    }

    public void submitTask(){

        requestSubmit = new SendRequestBody();
        requestSubmit.setEvent("X1ZKC3K6QTMB7PJ3KKGP5KTTEM3V");
        requestSubmit.setRecipient("a55ac196-d0c9-4398-b02e-814f8f9e9789");

        Gson gson = new Gson(); // Convert Java Objects into JSON and back

        JSONObject jsonNumberSubmit=new JSONObject();
        try{
            jsonNumberSubmit.put("phone_number","+919433093649");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        JSONObject jsonDataSubmit=new JSONObject();
        try{
            jsonDataSubmit.put("name","Satyaki");
            jsonDataSubmit.put("task1",taskTopics.get(0));
            jsonDataSubmit.put("status1",arraytaskstatus[0]);
            jsonDataSubmit.put("task2",taskTopics.get(1));
            jsonDataSubmit.put("status2",arraytaskstatus[1]);
            jsonDataSubmit.put("task3",taskTopics.get(2));
            jsonDataSubmit.put("status3",arraytaskstatus[2]);
            jsonDataSubmit.put("task4",taskTopics.get(3));
            jsonDataSubmit.put("status4",arraytaskstatus[3]);
            jsonDataSubmit.put("task5",taskTopics.get(4));
            jsonDataSubmit.put("status5",arraytaskstatus[4]);
            jsonDataSubmit.put("name1",participants.get(0));
            jsonDataSubmit.put("name2",participants.get(1));
            jsonDataSubmit.put("name3",participants.get(2));
            jsonDataSubmit.put("score1",part_Scores.get(0));
            jsonDataSubmit.put("score2",part_Scores.get(1));
            jsonDataSubmit.put("score3",part_Scores.get(2));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        String jsonProfile=jsonNumberSubmit.toString();
        String jsonData=jsonDataSubmit.toString();


        Map profileMap = gson.fromJson(jsonProfile, Map.class);
        requestSubmit.setProfile(new Gson().toJson(profileMap));

        Map dataMap = gson.fromJson(jsonData, Map.class);
        requestSubmit.setData(new Gson().toJson(dataMap));

        CourierSubmit courierSubmit=new CourierSubmit();
        courierSubmit.execute("");

    }


    public void readBasicData(){

        db.collection("Rooms").document("z8Oqe8CEnDcQsIKQupKL").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if(task.isSuccessful()){

                            DocumentSnapshot documentSnapshot=task.getResult();
                            BasicInformationClass basicInformationClass=documentSnapshot.toObject(BasicInformationClass.class);
                            duration=basicInformationClass.getDuration();
                            participants=basicInformationClass.getParticipants();
                            part_Scores=basicInformationClass.getScores();
                            totTasks=basicInformationClass.getTasks();
                            taskTopics=basicInformationClass.getTasktopics();
                            readyList(1,4);
                            readyTopics();
                            loginNotification();
                        }
                        else{
                            Log.i("Err",task.getException().toString());
                        }
                    }
                });

    }

    public void readyList(int a,int b){

     textName1.setText(participants.get(0));
     textScore1.setText(part_Scores.get(0));
     textName2.setText(participants.get(1));
     textScore2.setText(part_Scores.get(1));
     textName3.setText(participants.get(2));
     textScore3.setText(part_Scores.get(2));

     formChart(a,b);
    }

    public void readyTopics(){

        textTask1.setText(taskTopics.get(0));
        textTask2.setText(taskTopics.get(1));
        textTask3.setText(taskTopics.get(2));
        textTask4.setText(taskTopics.get(3));
        textTask5.setText(taskTopics.get(4));

    }


    public void formChart(int a,int b){

        ArrayList<PieEntry> listCompleted=new ArrayList<>();
        listCompleted.add(new PieEntry(a,"Completed"));
        listCompleted.add(new PieEntry(b,"Incomplete"));

        PieDataSet dataSet = new PieDataSet(listCompleted,"");

        PieData data = new PieData(dataSet);
        pieChart_Compete.setData(data);
        pieChart_Compete.setDrawEntryLabels(true);
        pieChart_Compete.setCenterText("Tasks: Incomplete V/S Complete");
        pieChart_Compete.setHoleRadius(50);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart_Compete.animateXY(5000, 5000);
        pieChart_Compete.invalidate();
    }


    public class CourierService extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            try {
                SendResponseBody response = new SendService().send(request);
                Log.i("Response",response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

  public class CourierSubmit extends AsyncTask<String,String,String>{

      @Override
      protected String doInBackground(String... strings) {
          try {
              SendResponseBody response = new SendService().send(requestSubmit);
              Log.i("Response",response.toString());

          } catch (IOException e) {
              e.printStackTrace();
          }

          return null;
      }
  }


}