package com.izen1231.ontime.layout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.izen1231.ontime.R;
import com.izen1231.ontime.domain.AddressDomain;
import com.ornach.nobobutton.NoboButton;
/* 인풋 출발지 도착지 정하기 */
public class SecondFragment extends Fragment {

    NoboButton  button;
    SendMessage sm;
    private PopupWindow mPopupWindow;

    public SecondFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.activity_input2, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final AddressDomain address = AddressDomain.getInstance();

        final EditText input_start = (EditText)view.findViewById(R.id.input_start);
        final EditText input_end = (EditText)view.findViewById(R.id.input_end);


        button = (NoboButton)view.findViewById(R.id.btn_second_next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder a_bulider = new AlertDialog.Builder(getActivity());
                a_bulider.setMessage("출발지 도착지 설정 완료!").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alert = a_bulider.create();
                alert.setTitle("완료 !!!");


                address.setSaddress(input_start.getText().toString());
                address.setEaddress(input_end.getText().toString());

                System.out.println("입력한 출발지 : " + address.getSaddress());
                System.out.println("입력한 도착지 : " + address.getEaddress());

                alert.show();
            }
        });

    }

    interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
//            sm = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

}
