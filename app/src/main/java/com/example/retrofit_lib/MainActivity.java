package com.example.retrofit_lib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ApiInterface apiInterface;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface=Api_Client.getClient().create(ApiInterface.class);
        textView=findViewById(R.id.textView);
    }
    public void getTodos(View view){
        Call<List<Todo>> call=apiInterface.getTodos();//to make a call\
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                Log.e(TAG, "onResponse: " + response.body() );
                //Log.d(TAG, "onResponse: "+list.size());
               List<Todo> list = response.body();
                for (Todo todo:list
                     ) {
                    textView.append("{id:"+todo.getId()+"\n");
                    textView.append("UserId:"+todo.getUserId()+"\n");
                    textView.append("title:"+todo.getTitle()+"\n");
                    textView.append("completed:"+todo.isCompleted()+"}\n");
                }
                }


            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });


    }
    public void getTodoUsingRouteParameter(View view){
        Call<Todo> todoCall=apiInterface.getTodo(3);
        todoCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Log.e(TAG, "onResponse: " + response.body());
                Todo todo=response.body();
                textView.setText("{UserId:"+todo.getUserId()+"\n");
                textView.append("id:"+todo.getId()+"\n");
                textView.append("title:"+todo.getTitle()+"\n");
                textView.append("completed:"+todo.isCompleted()+"}\n");
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }
    public void getTodoUsingQuery(View view){
     Call<List<Todo>> listCall=apiInterface.getTodoUsingQuery(1,true);
     listCall.enqueue(new Callback<List<Todo>>() {
         @Override
         public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
             Log.e(TAG, "onResponse: "+response.body() );


         }

         @Override
         public void onFailure(Call<List<Todo>> call, Throwable t) {
             Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
         }
     });

    }
    public void postTodo(View view){
        Todo todo=new Todo(3,2,"get me some milk",false);
        Call<Todo> postTodoCall=apiInterface.postTodo(todo);
        postTodoCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Log.e(TAG, "onResponse: "+response.body() );
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }
}
