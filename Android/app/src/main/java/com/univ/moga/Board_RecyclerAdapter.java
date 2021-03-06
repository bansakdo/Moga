package com.univ.moga;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.univ.moga.ClickedBoard.ClickedBoardActivity;
import com.univ.moga.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Board_RecyclerAdapter extends RecyclerView.Adapter<Board_RecyclerAdapter.ViewHolder> {

    private ArrayList<String> data;
    private JSONObject subject_professorJSONObject;
    private int boardType;

    Board_RecyclerAdapter(ArrayList<String> data, JSONObject jsonObject) {
        this.data = data;
        this.subject_professorJSONObject = jsonObject;
    }
    Board_RecyclerAdapter(ArrayList<String> data) {
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        View view;

        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.board_list_title);
            view = itemView;
        }
    }

    @NonNull
    @Override
    public Board_RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_board_list, parent, false);
        Board_RecyclerAdapter.ViewHolder vh = new Board_RecyclerAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final Board_RecyclerAdapter.ViewHolder holder, int position) {

        holder.title.setText(data.get(position));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        postIntent(v.getContext(), holder.title.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void postIntent(Context context, String title) {

        final Intent intent = new Intent(context, ClickedBoardActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("boardType", boardType);
        if(subject_professorJSONObject != null) {
            try {
                intent.putExtra("professor", subject_professorJSONObject.get(title).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } 

        context.startActivity(intent);
    }

    public void setSubject_professorJSONObject(JSONObject subject_professorJSONObject) {
        this.subject_professorJSONObject = subject_professorJSONObject;
    }

    public void setBoardType(int boardType) {
        this.boardType = boardType;
    }
}
